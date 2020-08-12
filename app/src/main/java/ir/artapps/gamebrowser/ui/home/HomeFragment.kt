package ir.artapps.gamebrowser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment  constructor() : Fragment(), HomeRecyclerViewAdapter.OnItemClickListener {

    private lateinit var mAdapter: HomeRecyclerViewAdapter
    private val viewModel: HomeViewModel by viewModel()

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
        mAdapter = HomeRecyclerViewAdapter()
        mAdapter.itemClickListener = this

        viewModel.apply {
            getGames(true)

            // observe updates of games
            gamesLiveData.observe(viewLifecycleOwner,
                Observer {

                    val g1 = Game().apply {
                        name = "BABY FOOD COOKING"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/23c08b39f8be40b0b08d88bfb5976cd4-512x384.jpeg"
                        banner =
                            "https://img.gamedistribution.com/23c08b39f8be40b0b08d88bfb5976cd4-512x384.jpeg"
                        downloadLink =
                            "https://html5.gamedistribution.com/23c08b39f8be40b0b08d88bfb5976cd4/"
                        isLocal = true
                    }

                    val g2 = Game().apply {
                        name = "PAK THE CAR"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/ec625c87a50a4f8dbd8c2d9f8839bfa2-512x384.jpeg"
                        banner =
                            "https://img.gamedistribution.com/ec625c87a50a4f8dbd8c2d9f8839bfa2-512x384.jpeg"
                        downloadLink =
                            "https://html5.gamedistribution.com/ec625c87a50a4f8dbd8c2d9f8839bfa2/"
                        isLocal = true
                    }

                    val g3 = Game().apply {
                        name = "THE ANGLER"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/93a6a4c9ae254283b2e23a06e4c25dc1-1280x550.jpeg"
                        banner =
                            "https://img.gamedistribution.com/93a6a4c9ae254283b2e23a06e4c25dc1-1280x550.jpeg"
                        downloadLink =
                            "https://html5.gamedistribution.com/93a6a4c9ae254283b2e23a06e4c25dc1/"
                        isLocal = true
                    }

                    val g4 = Game().apply {
                        name = "DINO ROCK"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/69fae384a8e2496ca38cbc0db98ac7ff-512x340.jpeg"
                        banner = preview
                        downloadLink = "https://html5.gamedistribution.com/69fae384a8e2496ca38cbc0db98ac7ff/"
                        isLocal = true
                    }

                    val g5 = Game().apply {
                        name = "BIRD FLYING"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/512223538e2d43618f8a2bcdf27b1103-512x340.jpeg"
                        banner = preview
                        downloadLink = "https://html5.gamedistribution.com/512223538e2d43618f8a2bcdf27b1103/"
                        isLocal = true
                    }

                    val g6 = Game().apply {
                        name = "FIND THESE GUYS"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/acfd74fe76ad4ef2918b34c52a8ea2d7-512x340.jpeg"
                        banner = preview
                        downloadLink = "https://html5.gamedistribution.com/acfd74fe76ad4ef2918b34c52a8ea2d7/"
                        isLocal = true
                    }

                    val g7 = Game().apply {
                        name = "PUZZLE BALL ROTATE"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/ccfcf8caf1894810ae6374ce9f6cc511-512x340.jpeg"
                        banner = preview
                        downloadLink = "https://html5.gamedistribution.com/ccfcf8caf1894810ae6374ce9f6cc511/"
                        isLocal = true
                    }

                    val g8 = Game().apply {
                        name = "COLORING BIRDS GAME"
                        rate = Rate(4.7f, 10, null)
                        preview =
                            "https://img.gamedistribution.com/3cd08d3dc9ee427db7b23879c11db22d-512x384.jpeg"
                        banner = preview
                        downloadLink = "https://html5.gamedistribution.com/3cd08d3dc9ee427db7b23879c11db22d/"
                        isLocal = true
                    }

                    val list: ArrayList<Game> = ArrayList()
                    list.add(g1)
                    list.add(g2)
                    list.add(g3)
                    list.add(g4)
                    list.add(g5)
                    list.add(g6)
                    list.add(g7)
                    list.add(g8)

                    for(item in it) {
                        if (item.name == "پرنده شل و ول")
                        list.add(item)
                    }

                    mAdapter.setGames(list)
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
                    when (position) {
                        0, 7, 18 -> return 2
                        else -> return 1
                    }
                }
            }

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

    // handle game item clicks and show detail fragment
    override fun onItemClick(view: View?, position: Int) {

        val item = mAdapter.items[position]
        val detailFragment =
            DetailFragment.newInstance(item)
        detailFragment.show(childFragmentManager, position.toString())
    }
}
