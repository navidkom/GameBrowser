package ir.artapps.gamebrowser.ui.profile

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import kotlinx.android.synthetic.main.edit_profile_fragment.toolbar
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.lang.Exception

class EditProfileFragment : BaseDialogFragment(), AvatarAdapter.OnItemClickListener {

    private val viewModel: ProfileViewModel by sharedViewModel()
    private var avatar: Int = 0

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

        val adapter = AvatarAdapter().apply {
            list = listOf( ContextCompat.getDrawable(requireContext(), R.drawable.avatar1), ContextCompat.getDrawable(requireContext(), R.drawable.avatar2), ContextCompat.getDrawable(requireContext(), R.drawable.avatar3),
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar4), ContextCompat.getDrawable(requireContext(), R.drawable.avatar5), ContextCompat.getDrawable(requireContext(), R.drawable.avatar6) ,
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar7), ContextCompat.getDrawable(requireContext(), R.drawable.avatar8), ContextCompat.getDrawable(requireContext(), R.drawable.avatar9) ,
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar10), ContextCompat.getDrawable(requireContext(), R.drawable.avatar11), ContextCompat.getDrawable(requireContext(), R.drawable.avatar12) ,
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar13), ContextCompat.getDrawable(requireContext(), R.drawable.avatar14), ContextCompat.getDrawable(requireContext(), R.drawable.avatar15) ,
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar16), ContextCompat.getDrawable(requireContext(), R.drawable.avatar17), ContextCompat.getDrawable(requireContext(), R.drawable.avatar18) ,
                ContextCompat.getDrawable(requireContext(), R.drawable.avatar19), ContextCompat.getDrawable(requireContext(), R.drawable.avatar20), ContextCompat.getDrawable(requireContext(), R.drawable.avatar21) ) as List<Drawable>

            mlistener = this@EditProfileFragment
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL, false )
        adapter.selectedItem = viewModel.profileLiveData.value?.avatar
        recyclerView.adapter = adapter

        viewModel.profileLiveData.value?.avatar?.let { recyclerView.scrollToPosition(it - 1) }

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

            val sex = if(rbBoy.isChecked) "boy" else "girl"
            viewModel.updateMeta(kidzyNameEdt.text.toString(), age, sex, avatar  )
        }


        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response == null) {
                signinParent.visibility = View.VISIBLE
                profileParent.visibility = View.GONE
                return@Observer
            }

            avatar = response.avatar

            signinParent.visibility = View.GONE
            profileParent.visibility = View.VISIBLE

            response.kidzyName?.let {
                kidzyNameEdt.setText( it )
            }

            response.age?.let {
                ageEdt.setText( it.toString() )
            }

            if(response.sex.toString() == "girl"){
                rbGirl.isChecked = true
            } else if (response.sex.toString() == "boy") {
                rbBoy.isChecked = true
            }
        })
    }

    override fun onItemClick(view: View?, position: Int) {
        avatar = position + 1
    }
}