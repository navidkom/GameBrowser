package ir.artapps.gamebrowser.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.repo.PodRepository
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment private constructor() : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

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

//        viewModel.getUserProfile()

        signin.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")
        }

        signout.setOnClickListener {
            viewModel.signOut()
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { response ->

            if (response == null) {
                signinParent.visibility = View.VISIBLE
                profileParent.visibility = View.GONE
                return@Observer
            }

            signinParent.visibility = View.GONE
            profileParent.visibility = View.VISIBLE

            loginText.setText(String.format("%s عزیز، به کیدزی خوش آمدی", response.name))
        })
    }
}