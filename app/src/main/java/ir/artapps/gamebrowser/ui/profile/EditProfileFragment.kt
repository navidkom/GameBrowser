package ir.artapps.gamebrowser.ui.profile

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.gson.Gson
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import kotlinx.android.synthetic.main.edit_profile_fragment.toolbar
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.*

class EditProfileFragment : BaseDialogFragment() {

    private val viewModel: ProfileViewModel by sharedViewModel()

    companion object {
        fun newInstance() = EditProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.edit_profile_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserProfile()

        toolbar?.apply {
            title = "ویرایش پروفایل"
            context?.let {
                setTitleTextColor(ContextCompat.getColor(it, R.color.white))
            }
            setNavigationIcon(R.drawable.ic_nav_back)
            setNavigationOnClickListener { dismiss() }
        }

        submitBtn.setOnClickListener {
            val age = try {
                ageEdt.text.toString().toInt()
            } catch (e: Exception) {
                null
            }

            viewModel.updateMeta(kidzyNameEdt.text.toString(), age, sexEdt.text.toString(), 0  )
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response == null) {
                signinParent.visibility = View.VISIBLE
                profileParent.visibility = View.GONE
                return@Observer
            }

            signinParent.visibility = View.GONE
            profileParent.visibility = View.VISIBLE

            response.kidzyName?.let {
                kidzyNameEdt.setText( it )
            }

            response.age?.let {
                ageEdt.setText( it.toString() )
            }

            response.sex?.let {
                sexEdt.setText( it )
            }
        })
    }
}