package ir.artapps.gamebrowser.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.gson.Gson
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.EventBus
import ir.artapps.gamebrowser.entities.pod.ClientMetadata
import ir.artapps.gamebrowser.repo.PodRepository
import ir.artapps.gamebrowser.ui.home.FavoriteFragment
import ir.artapps.gamebrowser.ui.signin.SignInActivity
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by sharedViewModel()

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

        viewModel.getUserProfile()

        signin.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")
        }

        signout.setOnClickListener {
            viewModel.signOut()
        }

        favoriteBtn.setOnClickListener {
            FavoriteFragment.newInstance().show(childFragmentManager, "")
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
        })
    }
}