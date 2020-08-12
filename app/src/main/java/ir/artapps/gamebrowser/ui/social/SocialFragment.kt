package ir.artapps.gamebrowser.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.artapps.gamebrowser.R
import org.koin.android.viewmodel.ext.android.viewModel

class SocialFragment private constructor(): Fragment() {

    val viewModel : SocialViewModel by viewModel()

    companion object {
        fun newInstance() = SocialFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.social_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}