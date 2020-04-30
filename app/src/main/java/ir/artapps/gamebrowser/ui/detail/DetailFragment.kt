package ir.artapps.gamebrowser.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.moviedb.ui.detail.DetailViewModel

import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by navid
 */
class DetailFragment : BaseDialogFragment() {
    private var game: Game? = null
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.detail_fragment, container, false)
        return v
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            if (containsKey(MODEL_ARG_KEY)) {
                game = getParcelable(MODEL_ARG_KEY)
            }
        }

        toolbar?.apply {
            title = game?.name
            context?.let {
                setTitleTextColor(ContextCompat.getColor(it, R.color.colorWhite))
            }
            setNavigationIcon(R.drawable.ic_nav_back)
            setNavigationOnClickListener { dismiss() }
        }

        collapsingToolbarLayout?.apply {
            context?.let {
                setCollapsedTitleTextColor(ContextCompat.getColor(it, R.color.colorWhite))
                setExpandedTitleColor(ContextCompat.getColor(it, R.color.colorWhite))
            }
        }

        appBarLayout?.setExpanded(false)

        detailViewModel.apply {
            gameLiveData.observe(
                viewLifecycleOwner,
                Observer { game -> setNetData(game) })
            game?.id?.let {
                getDetail(it)
            }
            errorLiveData.observe(viewLifecycleOwner, Observer {
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            })
        }
    }

    // set the detail game data that fetched from remote server
    private fun setNetData(game: Game?) {
        game?.apply {
            banner?.let {
                Glide.with(this@DetailFragment).load( it)

                    // use glide listener to expand appBarLayout when resource is ready
                    .listener(object : RequestListener<Drawable> {


                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            appBarLayout?.setExpanded(true)
                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                    })
                    .into(photoImageView)
            }

            name?.let {
                titleTextView.text = it
            }

            gamePlayDesc?.let {
                summeryTextView.text = it
            }

//            ?.let {
//                setDetailTextView(getString(R.string.language), it)
//            }
//
//            budget?.let {
//                setDetailTextView(getString(R.string.budget), it)
//            }
//
//            releaseDate?.let {
//                setDetailTextView(getString(R.string.release_date), it)
//            }
//
//            voteAverage?.let {
//                setDetailTextView(getString(R.string.average_vote), it)
//            }
        }
    }

    private fun setDetailTextView(name: String, value: Any) {
        detailTextView.text = "${detailTextView.text}\n$name $value"
    }

    companion object {
        private const val MODEL_ARG_KEY = "ModelKey"
        fun newInstance(game: Game?): DetailFragment {
            val detailFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(MODEL_ARG_KEY, game)
            detailFragment.arguments = bundle
            return detailFragment
        }
    }
}