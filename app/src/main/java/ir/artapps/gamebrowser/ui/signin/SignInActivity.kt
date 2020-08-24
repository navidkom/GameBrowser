package ir.artapps.gamebrowser.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *   Created by Navid Komijani
 *   on 19,August,2020
 */

class SignInActivity : AppCompatActivity() {


//    val viewModel: SignInViewModel by viewModel()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.signin_activity)
//
//        viewModel.getUserProfile()
//        viewModel.profileLiveData.observe(this, Observer {
//            if (it == null) {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, SigninFragment.newInstance())
//                    .commitNow()
//            } else {
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }
//        })
//    }
}