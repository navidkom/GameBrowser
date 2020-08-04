package ir.artapps.gamebrowser.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class SigninFragment private constructor() : BaseDialogFragment() {

    companion object {
        fun newInstance() = SigninFragment()
    }

    val viewModel : SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =

            inflater.inflate(R.layout.login_fragment, container, false)

        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            dismiss()
        })

        toolbar.title = "ورود"
        toolbar.setNavigationIcon(R.drawable.ic_nav_back)
        toolbar.setNavigationOnClickListener {
            this@SigninFragment.dismiss()
        }

        webview.apply {
            settings.javaScriptEnabled = true
            settings.pluginState = WebSettings.PluginState.ON
            settings.allowFileAccess = true
            settings.loadsImagesAutomatically = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            webChromeClient = WebChromeClient()
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    if(url.startsWith("http://www.kidzy.ir/redirect_app?code=")) {
                        Log.d("token" , url)
                        val token =  url.substring(url.indexOf("?code=") + 6)
                        App.loginCode = token
                        viewModel.getUserProfile(token)
                        return true
                    }
                    return false
                }
            }

            val url =
                "https://accounts.pod.ir/oauth2/authorize/?client_id=18060069b4bf248759168bc24000d57c7&response_type=code&redirect_uri=http://www.kidzy.ir/redirect_app&scope=social&scope=profile:write"
            loadUrl(url)
        }
    }

}