package ir.artapps.gamebrowser.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.logEvent
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.home.FavoriteFragment
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by sharedViewModel()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = Firebase.analytics

        viewModel.getUserProfile()

        signin.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")
        }

        signout.setOnClickListener {
            viewModel.signOut()
            firebaseAnalytics.logEvent("sign_out"){}
        }

        favoriteBtn.setOnClickListener {
            FavoriteFragment.newInstance().show(childFragmentManager, "")
            firebaseAnalytics.logEvent("favorite_list_click"){}
        }

        profileBtn.setOnClickListener {
            EditProfileFragment.newInstance().show(childFragmentManager, "")
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response == null) {
                signinParent.visibility = View.VISIBLE
                profileParent.visibility = View.GONE
                return@Observer
            }

            signinParent.visibility = View.GONE
            profileParent.visibility = View.VISIBLE


            loginText.setText(String.format( Locale("fa"),"%s\n%s", response.kidzyName, " به کیدزی خوش آمدی"))

            avatar.setImageResource( when(response.avatar){
                1 -> R.drawable.avatar1
                2 -> R.drawable.avatar2
                3 -> R.drawable.avatar3
                4 -> R.drawable.avatar4
                5 -> R.drawable.avatar5
                6 -> R.drawable.avatar6
                7 -> R.drawable.avatar7
                8 -> R.drawable.avatar8
                9 -> R.drawable.avatar9
                10 -> R.drawable.avatar10
                11 -> R.drawable.avatar11
                12 -> R.drawable.avatar12
                13 -> R.drawable.avatar13
                14 -> R.drawable.avatar14
                15 -> R.drawable.avatar15
                16 -> R.drawable.avatar16
                17 -> R.drawable.avatar17
                18 -> R.drawable.avatar18
                19 -> R.drawable.avatar19
                20 -> R.drawable.avatar20
                21 -> R.drawable.avatar21
                else -> R.drawable.avatar1
            })
        })
    }
}