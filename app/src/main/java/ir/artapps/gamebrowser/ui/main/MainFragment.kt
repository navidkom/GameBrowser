package ir.artapps.gamebrowser.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MainRecyclerViewAdapter.OnItemClickListener{

    private val linearLayoutManager = GridLayoutManager(context, 3)
    private lateinit var mAdapter: MainRecyclerViewAdapter

    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
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
        mAdapter = MainRecyclerViewAdapter()
        mAdapter.itemClickListener = this

        viewModel.apply {
            getGames(true)

            // observe updates of games
            gamesLiveData.observe(viewLifecycleOwner,
                Observer {

                    mAdapter.setGames(it)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchMenuItem = menu.findItem(R.id.search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getGames(true, query)
                refreshLayout.isRefreshing = true
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            viewModel.getGames(true)
            refreshLayout.isRefreshing = true
            false
        }
    }

    // handle game item clicks and show detail fragment
    override fun onItemClick(view: View?, position: Int) {
        val detailFragment =
            DetailFragment.newInstance(mAdapter.items[position])
        detailFragment.show(childFragmentManager, position.toString())
    }
}
