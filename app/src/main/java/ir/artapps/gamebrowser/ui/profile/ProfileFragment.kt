package ir.artapps.gamebrowser.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.home.FavoriteFragment
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ProfileFragment : Fragment(), EditProfileFragment.AvatarSelection {
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


        signInBtn.setOnClickListener {
            SigninFragment.newInstance().show(childFragmentManager, "")

            Firebase.analytics.logEvent("login_profile") {
            }
        }


        viewModel.getUserProfile()

        moreBtn.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            val inflater = popupMenu.menuInflater
            inflater.inflate(R.menu.profile, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { it ->
                when (it.itemId) {
                    R.id.signout -> {
                        viewModel.signOut()
                        Firebase.analytics.logEvent("sign_out") {}
                    }
                }
                true
            }
        }

        submitBtn.setOnClickListener {
            viewModel.updateMeta(
                nameEditText.text.toString(),
                viewModel.tempAge,
                viewModel.tempSex,
                viewModel.tempAvatar
            )
        }

        favoriteBtn.setOnClickListener {
            FavoriteFragment.newInstance().show(childFragmentManager, "")
            Firebase.analytics.logEvent("favorite_list_click") {}
        }


        viewModel.updateProfileLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "اطلاعات به روز شد", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "خطایی رخ داد", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response == null) {
                emptyView.visibility = View.VISIBLE
                return@Observer
            }

            emptyView.visibility = View.GONE

            viewModel.tempAge = response.age
            viewModel.tempSex = response.sex

            nameEditText.setText(response.kidzyName)
            setAge(response.age)

            setAvatar(response.avatar)

            if (response.sex == "girl") {
                sexViewBoy.select(false)
                sexViewGirl.select(true)
            } else if (response.sex == "boy") {
                sexViewBoy.select(true)
                sexViewGirl.select(false)
            }

            avatar.setOnClickListener {
                EditProfileFragment.newInstance().apply {
                    listener = this@ProfileFragment
                }.show(childFragmentManager, "")
            }
        })

        ageView1.setOnClickListener {
            setAge(5)
        }
        ageView2.setOnClickListener {
            setAge(6)
        }
        ageView3.setOnClickListener {
            setAge(7)
        }
        ageView4.setOnClickListener {
            setAge(8)
        }
        ageView5.setOnClickListener {
            setAge(9)
        }
        ageView6.setOnClickListener {
            setAge(10)
        }
        ageView7.setOnClickListener {
            setAge(11)
        }

        sexViewBoy.setOnClickListener {
            sexViewBoy.select(true)
            sexViewGirl.select(false)
            viewModel.tempSex = "boy"
        }

        sexViewGirl.setOnClickListener {
            sexViewBoy.select(false)
            sexViewGirl.select(true)
            viewModel.tempSex = "girl"
        }
    }

    fun setAvatar(index: Int) {
        viewModel.tempAvatar = index
        avatar.setImageResource(
            when (index) {
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
            }
        )
    }

    fun setAge(age: Int) {
        viewModel.tempAge = age
        ageView1.select(false)
        ageView2.select(false)
        ageView3.select(false)
        ageView4.select(false)
        ageView5.select(false)
        ageView6.select(false)
        ageView7.select(false)

        when (age) {
            in Int.MIN_VALUE..5 -> ageView1.select(true)
            6 -> ageView2.select(true)
            7 -> ageView3.select(true)
            8 -> ageView4.select(true)
            9 -> ageView5.select(true)
            10 -> ageView6.select(true)
            in 10..Int.MAX_VALUE -> ageView7.select(true)
        }
    }

    private fun TextView.select(select: Boolean) {
        if (select) {
            background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_curved_filled)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        } else {
            background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_curved)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
        }
    }

    private fun ImageView.select(select: Boolean) {
        if (select) {
            background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_curved_filled)
            setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        } else {
            background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_curved)
            setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
    }

    override fun avatarSelectesd(index: Int) {
        setAvatar(index)
    }
}