package ir.artapps.gamebrowser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.home.HomeFragment
import ir.artapps.gamebrowser.ui.profile.ProfileFragment
import ir.artapps.gamebrowser.ui.social.SocialFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        setSupportActionBar(findViewById(R.id.my_toolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }


        bottom_nav.itemIconTintList = null;
        bottom_nav.setOnNavigationItemSelectedListener {
            val fragment: Fragment = when (it.itemId) {
                R.id.navigation_home -> HomeFragment.newInstance()
                R.id.navigation_notifications -> SocialFragment.newInstance()
                R.id.navigation_sms -> ProfileFragment.newInstance()
                else -> HomeFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
            true
        }
    }
}
