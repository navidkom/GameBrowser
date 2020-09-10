package ir.artapps.gamebrowser.ui.detail

import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.ui.WebViewActivity
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment1.*
import kotlinx.android.synthetic.main.detail_fragment1.dislikeImageView
import kotlinx.android.synthetic.main.detail_fragment1.dislikeTextView
import kotlinx.android.synthetic.main.detail_fragment1.favoriteImageView
import kotlinx.android.synthetic.main.detail_fragment1.inputRatingBar
import kotlinx.android.synthetic.main.detail_fragment1.likeImageView
import kotlinx.android.synthetic.main.detail_fragment1.likeTextView
import kotlinx.android.synthetic.main.detail_fragment1.photoImageView
import kotlinx.android.synthetic.main.detail_fragment1.photoImageViewBack
import kotlinx.android.synthetic.main.detail_fragment1.play_btn
import kotlinx.android.synthetic.main.detail_fragment1.ratingText
import kotlinx.android.synthetic.main.detail_fragment1.videoView
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by navid
 */
class DetailFragment : BaseDialogFragment() {
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment1, container, false)
    }

    var listener: Listener? = null


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = Firebase.analytics
        arguments?.apply {
            if (containsKey(MODEL_ARG_KEY)) {
                viewModel.game = getParcelable(MODEL_ARG_KEY)
            }
        }

//        toolbar?.apply {
//            title = viewModel.game?.name
//            context?.let {
//                setTitleTextColor(ContextCompat.getColor(it, R.color.white))
//            }
//            setNavigationIcon(R.drawable.ic_nav_back)
//            setNavigationOnClickListener { dismiss() }
//        }

        gameTitle.setText(viewModel.game?.name)

        try {
            Glide.with(this@DetailFragment).load(viewModel.game?.metadata?.image?.get(0))
                .into(photoImageView)

            Glide.with(this@DetailFragment).load(viewModel.game?.metadata?.image?.get(0))
                .into(photoImageViewBack)

            Glide.with(this@DetailFragment).load(viewModel.game?.metadata?.image?.get(0))
                .into(avatarImage)
        } catch (e: Exception) {
        }

        setNetData(viewModel.game)

        viewModel.apply {


            gameLiveData.observe(
                viewLifecycleOwner,
                Observer { game -> setNetData(game) })

            if (viewModel.podRepository.token != null) {
                game?.entityId?.let {
                    getDetail(it.toLong())
                }
            }

            errorLiveData.observe(viewLifecycleOwner, Observer {
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            })
        }

        play_btn.setOnClickListener {

            firebaseAnalytics.logEvent("play_game_click"){
                param(FirebaseAnalytics.Param.ITEM_ID, viewModel.game?.entityId.toString() )
                param(FirebaseAnalytics.Param.ITEM_NAME, viewModel.game?.name + "")
            }

            if (viewModel.podRepository.playPodProfile == null && viewModel.game?.metadata!!.isPlayPod) {
                loginPlayPodDialog()
            } else {
                viewModel.getGamePlayURL(viewModel.game!!).observe(viewLifecycleOwner, Observer {
                    if (it.isNullOrEmpty()) {
                        return@Observer
                    }

                    val intent = Intent(activity, WebViewActivity::class.java)
                    intent.putExtra("url", it)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    activity?.startActivity(intent)
                })
            }
        }


        likeImageView.setOnClickListener {
            if (!loginDialog()) {
                viewModel.like()
            }
        }

        dislikeImageView.setOnClickListener {
            if (!loginDialog()) {
                viewModel.dislike()
            }
        }


        inputRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (!loginDialog()) {
                viewModel.rate(rating)
            }
        }
    }

    // set the detail game data that fetched from remote server
    private fun setNetData(game: Game?) {
        game?.apply {

            rate?.rate?.let {
                ratingText.text = String.format("%.1f", it);
            }

            rate?.myRate?.let {
                inputRatingBar.rating = it
            }

            description?.let {
                summaryTextView.visibility = View.VISIBLE
                summaryTextView.text = String.format("%s\n%s", getString(R.string.description), it)
            }

            metadata?.instruction?.let {
                gameplayTextView.visibility = View.VISIBLE
                gameplayTextView.text = String.format("%s\n%s", getString(R.string.gameplay), it)
            }

            metadata?.parentalInfo?.let {
                parentalInfoTextView.visibility = View.VISIBLE
                parentalInfoTextView.text =
                    String.format("%s\n%s", getString(R.string.parentalInfo), it)
            }

//            detailTextView.text = ""
            metadata?.ageRange?.let {
                setDetailTextView(getString(R.string.age_range), " $it سال به بالا")
            }

            metadata?.genres?.let { it ->
                setDetailTextView(getString(R.string.game_genre), it.contentToString())
            }

            metadata?.types?.let { it ->
                setDetailTextView(getString(R.string.game_type), it.contentToString())
            }

            metadata?.video?.let {
                playVideo(it)
            }

            tags?.let { it ->
                if (it.isNotEmpty()) {
                    setDetailTextView(getString(R.string.game_tags), it.contentToString())
                }
            }

//            entityId?.let { it ->
//                setDetailTextView("entityId:", it)
//            }

//            setDetailTextView("id:", id)

            likeTextView.text = numOfLikes.toString()
            dislikeTextView.text = numOfDisLikes.toString()

            if (userPostInfo?.liked != null && !userPostInfo?.liked!!) {
                likeImageView.alpha = 0.5f
                dislikeImageView.alpha = 1f
            } else  {
                likeImageView.alpha = 1f
                dislikeImageView.alpha = 0.5f
            }

            if (userPostInfo?.favorite != null && userPostInfo?.favorite!!) {
                favoriteImageView.setImageResource(R.drawable.ic_fav_filled)
            } else {
                favoriteImageView.setImageResource(R.drawable.ic_fav)
            }

            favoriteImageViewParent.setOnClickListener {
                if (!loginDialog()) {
                    viewModel.favorite(userPostInfo?.favorite!!)
                }
            }

            color?.let {
                favoriteImageView.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                play_btn.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                likeImageView.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

                likeTextView.setTextColor(ContextCompat.getColor(requireContext(), it))
                dislikeTextView.setTextColor(ContextCompat.getColor(requireContext(), it))
                gameTitle.setTextColor(ContextCompat.getColor(requireContext(), it))


                dislikeImageView.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

                avatarFrame.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                imageFrame1.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                imageFrame2.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                imageFrame3.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                imageFrame4.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

//                ratingBar.setBackgroundColor(
//                    ContextCompat.getColor(requireContext(), it)
//                )
//                ratingBar.solidColor = ContextCompat.getColor(requireContext(), it)

                ratingBar.progressDrawable.setColorFilter(
                    ContextCompat.getColor(requireContext(), it),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun setDetailTextView(name: String, value: Any) {
//        detailTextView.text = "${detailTextView.text}\n$name $value"
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

    override fun onStop() {
        super.onStop()
        listener?.let {
            it.onStop()
        }
    }

    interface Listener {
        fun onStop()
    }

    private fun playVideo(url: String) {
        try {

//            videoView.visibility = View.VISIBLE
            activity?.window?.setFormat(PixelFormat.TRANSLUCENT)
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoView)
            val video: Uri = Uri.parse(url)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(video)
            videoView.requestFocus()
            videoView.setOnPreparedListener {
//                progressDialog.dismiss()
                videoView.start()
                videoView.visibility = View.VISIBLE
                photoImageView.visibility = View.GONE
            }
        } catch (e: java.lang.Exception) {
//            progressDialog.dismiss()
            println("Video Play Error :$e")
            videoView.visibility = View.INVISIBLE
            photoImageView.visibility = View.VISIBLE
//            finish()
        }
    }

    private fun loginDialog(): Boolean {
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialog.setTitle("ورود به حساب کاربری")
        dialog.setMessage("برای انجام این عملیات نیاز است وارد حساب کاربری خود شوید")


        firebaseAnalytics.logEvent("social_login"){
            param(FirebaseAnalytics.Param.ITEM_ID, viewModel.game?.entityId.toString() )
            param(FirebaseAnalytics.Param.ITEM_NAME, viewModel.game?.name + "")
        }

        dialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "باشه"
        ) { _, _ ->
            SigninFragment.newInstance().show(childFragmentManager, "")

            firebaseAnalytics.logEvent("social_login_accepted"){
                param(FirebaseAnalytics.Param.ITEM_ID, viewModel.game?.entityId.toString() )
                param(FirebaseAnalytics.Param.ITEM_NAME, viewModel.game?.name + "")
            }
        }

        dialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "بیخیال"
        ) { dialog, _ ->
            dialog.dismiss()

            firebaseAnalytics.logEvent("social_login_rejected"){
                param(FirebaseAnalytics.Param.ITEM_ID, viewModel.game?.entityId.toString() )
                param(FirebaseAnalytics.Param.ITEM_NAME, viewModel.game?.name + "")
            }
        }

        return if (viewModel.podRepository.profile == null) {
            dialog.show()
            true
        } else false
    }

    private fun loginPlayPodDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialog.setTitle("ورود به حساب پلی پاد")
        dialog.setMessage("برای انجام این بازی نیاز است وارد حساب پلی پاد  شوید")

        dialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "باشه"
        ) { _, _ ->
            SigninFragment.newInstance(1).show(childFragmentManager, "")
        }

        dialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "بیخیال"
        ) { dialog, _ ->
            dialog.dismiss()
        }

        dialog.show()
    }
}

fun <T> List<T>?.contentToString(): String {
    var value = ""
    this?.forEachIndexed { index, s ->
        value = if (index == 0) {
            s.toString()
        } else {
            "$value ، ${s.toString()}"
        }
    }
    return value
}