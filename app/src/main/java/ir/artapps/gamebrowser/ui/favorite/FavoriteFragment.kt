package ir.artapps.gamebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.ui.detail.DetailFragment
import ir.artapps.gamebrowser.ui.main.MainActivity
import kotlinx.android.synthetic.main.favorite_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseDialogFragment(), FavoriteRecyclerViewAdapter.OnItemClickListener {

    private lateinit var mAdapter: FavoriteRecyclerViewAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.favorite_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = FavoriteRecyclerViewAdapter()
        mAdapter.itemClickListener = this


        toolbar.title = "علاقه‌مندی ها"
        if (activity is MainActivity) {
            toolbar.setNavigationIcon(R.drawable.ic_nav_back)
            toolbar.setNavigationOnClickListener {
                this@FavoriteFragment.dismiss()
            }
        }

        viewModel.apply {
            getGames()
            // observe updates of games
            gamesLiveData.observe(viewLifecycleOwner,
                Observer { list ->
                    val filteredList = list.filter { it.userPostInfo?.favorite == true }
                    mAdapter.setGames(filteredList)
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

        recyclerView?.apply {
            val linearLayoutManager = GridLayoutManager(context, 2)
            linearLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }

            layoutManager = linearLayoutManager
            adapter = mAdapter
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    linearLayoutManager.apply {
//
//                        // check if recycler view moves to end and send request for next page
//                        if (childCount + findFirstVisibleItemPosition() >= itemCount
//                            && !viewModel.loading
//                            && !viewModel.lastPage && dy > 0
//                        ) {
//                            viewModel.getGames()
//                            viewModel.loading = true
//                            mAdapter.loading = true
//                        }
//                    }
//                }
//            })
        }

        // pull to refresh
        refreshLayout.setOnRefreshListener {
            viewModel.getGames()
        }
    }

    // handle game item clicks and show detail fragment
    override fun onItemClick(view: View?, position: Int) {
        val item = mAdapter.items[position]
        val detailFragment = DetailFragment.newInstance(item)
        detailFragment.show(childFragmentManager, position.toString())
        detailFragment.listener =  object : DetailFragment.Listener{
            override fun onStop() {
                viewModel.getGames()
            }
        }
    }
}
