package ir.artapps.gamebrowser.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment

/**
 *   Created by Navid Komijani
 *   on 06,September,2020
 */
class SplashFragment: BaseDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}