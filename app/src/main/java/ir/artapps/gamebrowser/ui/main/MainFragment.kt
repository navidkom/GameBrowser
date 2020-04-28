package ir.artapps.gamebrowser.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.detail.VenueDetailFragment
import ir.artapps.gamebrowser.util.DistanceUtil
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), MainRecyclerViewAdapter.OnItemClickListener {

    private val linearLayoutManager = LinearLayoutManager(context)
    private lateinit var mAdapter: MainRecyclerViewAdapter

    private val venueViewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = MainRecyclerViewAdapter()
        mAdapter.itemClickListener = this


        venueViewModel.apply {

            getVenues(true)

            // observe updates of venues
            venuesLiveData.observe(viewLifecycleOwner,
                Observer {

                    mAdapter.setVenues(it)
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
                            && !venueViewModel.loading
                            && !venueViewModel.lastPage && dy > 0
                        ) {
                            venueViewModel.getVenues(false)
                            venueViewModel.loading = true
                            mAdapter.loading = true
                        }
                    }
                }
            })
        }

        // pull to refresh
        refreshLayout.setOnRefreshListener {
            venueViewModel.getVenues(true)
        }
    }

    // handle venue item clicks and show detail fragment
    override fun onItemClick(view: View?, position: Int) {
//        val detailFragment: VenueDetailFragment =
//            VenueDetailFragment.newInstance(mAdapter.items[position])
//        detailFragment.show(childFragmentManager, position.toString())
    }
}
