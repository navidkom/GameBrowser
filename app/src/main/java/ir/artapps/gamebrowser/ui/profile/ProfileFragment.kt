package ir.artapps.gamebrowser.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ir.artapps.bazarreview.ui.customview.ProfileItemsCustomView
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R
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

        signin.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")
        }

        signout.setOnClickListener {
            App.profile.value = null
            App.token = null
        }

        App.profile.observe(viewLifecycleOwner, Observer { response ->

            if (response == null) {
                signinParent.visibility = View.VISIBLE
                profileParent.visibility = View.GONE
                return@Observer
            }

            signinParent.visibility = View.GONE
            profileParent.visibility = View.VISIBLE

            loginText.setText(String.format("%s %s عزیز، به کیدزی خوش آمدی", response.firstName, response.lastName))

//            response?.firstName?.let {
//                profileViewContainer.addView(
//                    ProfileItemsCustomView(requireContext(), "نام", it, null)
//                )
//            }
//
//            response?.lastName?.let {
//                profileViewContainer.addView(
//                    ProfileItemsCustomView(requireContext(), "نام خانوادگی", it, null)
//                )
//            }

        })
    }
}