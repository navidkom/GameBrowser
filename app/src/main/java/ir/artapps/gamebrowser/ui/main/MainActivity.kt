package ir.artapps.gamebrowser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseActivity
import ir.artapps.gamebrowser.ui.home.HomeFragment
import ir.artapps.gamebrowser.ui.profile.ProfileFragment
import ir.artapps.gamebrowser.ui.social.ChatFragment
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    lateinit var homeFragment: Fragment
    lateinit var socialFragment: Fragment
    lateinit var profileFragment: Fragment

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        homeFragment = HomeFragment.newInstance()
        socialFragment = ChatFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        viewModel.getUserProfile()
        bottom_nav.itemIconTintList = null;
        bottom_nav.setOnNavigationItemSelectedListener {


            val fragment: Fragment = when (it.itemId) {
                R.id.navigation_home -> homeFragment
                R.id.navigation_sms -> socialFragment
                R.id.navigation_notifications -> profileFragment
                else -> HomeFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
            true
        }

        if (savedInstanceState == null) {
            bottom_nav.selectedItemId = R.id.navigation_home
        }

//        my_toolbar.signinBtn.setOnClickListener {
//            SigninFragment.newInstance().show(supportFragmentManager, "")
//        }
//

        viewModel.profileLiveData.observe(this, Observer { response ->
//            if (response == null) {
//                my_toolbar.signIn = false
//                return@Observer
//            }
//
//            my_toolbar.signIn = true
//            my_toolbar.name = response.kidzyName

            val avatar = when (response?.avatar) {
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
//            my_toolbar.image = ContextCompat.getDrawable(this, avatar)

        })


//        val frag = SplashFragment()
//        frag.show(supportFragmentManager, "")
    }
}
