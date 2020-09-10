package ir.artapps.gamebrowser.ui.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseDialogFragment(), HomeRecyclerViewAdapter.OnItemClickListener, HomeViewPagerAdapter.OnItemClickListener {

    private lateinit var mAdapter: HomeRecyclerViewAdapter
    private lateinit var mPagerAdapter: HomeViewPagerAdapter
    private val viewModel: HomeViewModel by viewModel()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = Firebase.analytics
        mAdapter = HomeRecyclerViewAdapter()
        mPagerAdapter = HomeViewPagerAdapter()
        mAdapter.itemClickListener = this
        mPagerAdapter.itemClickListener = this

        refreshLayout.isRefreshing = true
        
        viewModel.apply {
            getGames(true)
            // observe updates of games
            gamesLiveData.observe(viewLifecycleOwner,
                Observer {
                    popularTxt.visibility = View.VISIBLE
                    mAdapter.setGames(it.subList(3, it.size))
                    mPagerAdapter.setGames(it.subList(0, 3))
                    refreshLayout.isRefreshing = false
                    mAdapter.loading = false
                    loading = false
                })

            // observe and show errors
            errorLiveData.observe(viewLifecycleOwner, Observer {
                loading = false
                mAdapter.loading = false
                refreshLayout.isRefreshing = false
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            })
        }

        viewPager.apply {

            adapter = mPagerAdapter

            offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                // Next line scales the item's height. You can remove it if you don't want this effect
                page.scaleY = 1 - (0.0f * kotlin.math.abs(position))
                // If you want a fading effect uncomment the next line:
                // page.alpha = 0.25f + (1 - abs(position))
            }
            setPageTransformer(pageTransformer)

// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
            val itemDecoration = HorizontalMarginItemDecoration(
                context,
                R.dimen.viewpager_current_item_horizontal_margin
            )
            addItemDecoration(itemDecoration)
        }

        recyclerView?.apply {
            val linearLayoutManager = GridLayoutManager(context, 3)

            layoutManager = linearLayoutManager
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    linearLayoutManager.apply {

                        // check if recycler view moves to end and send request for next page
                        if (childCount + findFirstVisibleItemPosition() >= itemCount
                            && !viewModel.loading
                            && !viewModel.lastPage && dy > 0
                        ) {
                            viewModel.getGames(false)
                            viewModel.loading = true
                            mAdapter.loading = true
                        }
                    }
                }
            })
        }

        // pull to refresh
        refreshLayout.setOnRefreshListener {
            viewModel.getGames(true)
        }
    }

    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {

        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }

    }


    override fun onViewPagerItemClick(view: View?, position: Int) {
        val item = mPagerAdapter.items[position]
        val detailFragment =
            DetailFragment.newInstance(item)
        detailFragment.show(childFragmentManager, position.toString())

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, item.entityId.toString())
            param(FirebaseAnalytics.Param.ITEM_NAME, item.name + "")
        }
    }

    override fun onRecyclerItemClick(view: View?, position: Int) {
        val item = mAdapter.items[position]
        val detailFragment =
            DetailFragment.newInstance(item)
        detailFragment.show(childFragmentManager, position.toString())

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, item.entityId.toString())
            param(FirebaseAnalytics.Param.ITEM_NAME, item.name + "")
        }
    }
}
