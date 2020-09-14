package ir.artapps.gamebrowser.ui.profile

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
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

class EditProfileFragment : DialogFragment(), AvatarAdapter.OnItemClickListener {

    private val viewModel: ProfileViewModel by sharedViewModel()
    lateinit var listener : AvatarSelection
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
        adapter.selectedItem = viewModel.tempAvatar
        recyclerView.adapter = adapter

        viewModel.tempAvatar.let { recyclerView.scrollToPosition(it - 1) }

        toolbar?.apply {
            title = "انتخاب آواتار"
            context?.let {
                setTitleTextColor(ContextCompat.getColor(it, R.color.white))
            }
            setNavigationIcon(R.drawable.ic_cross)

            setNavigationOnClickListener { dismiss() }
        }
    }

    override fun onItemClick(view: View?, position: Int) {
        viewModel.tempAvatar = position + 1
        if(::listener.isInitialized){
            listener.avatarSelectesd(viewModel.tempAvatar)
        }
        dismiss()
    }

    interface AvatarSelection{
        fun avatarSelectesd(index: Int)
    }
}