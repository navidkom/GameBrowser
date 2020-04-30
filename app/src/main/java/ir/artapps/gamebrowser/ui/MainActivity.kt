package ir.artapps.gamebrowser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }



}
