package ir.artapps.gamebrowser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.home.HomeFragment
import ir.artapps.gamebrowser.ui.profile.ProfileFragment
import ir.artapps.gamebrowser.ui.signin.SigninFragment
import ir.artapps.gamebrowser.ui.social.ChatFragment
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

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

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, homeFragment)
                .commitNow()
        }

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

        my_toolbar.signinBtn.setOnClickListener {
            SigninFragment.newInstance().show(supportFragmentManager, "")
        }

        viewModel.profileLiveData.observe(this, Observer {
            if (it == null) {
                my_toolbar.signIn = false
            } else {
                my_toolbar.signIn = true
                my_toolbar.name = it.name
                Snackbar.make(root, "ورود با موفقیت انجام شد", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}
