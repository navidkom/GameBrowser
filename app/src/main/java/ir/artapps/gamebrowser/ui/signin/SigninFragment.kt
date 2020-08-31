package ir.artapps.gamebrowser.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.lifecycle.Observer
import com.google.gson.Gson
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.base.BaseDialogFragment
import ir.artapps.gamebrowser.entities.PlayPodResponseLogin
import ir.artapps.gamebrowser.ui.main.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SigninFragment : BaseDialogFragment() {

    val PLAYPOD_LOGIN_URL =
        "https://accounts.pod.ir/oauth2/authorize/?client_id=16807y864b4ab6a05a80d602f5b6d7&response_type=code&redirect_uri=https://service-play.pod.ir:443/Pages/Auth/SSOCallback/Default.aspx&scope=phone%20profile%20social:write"
    val LOGIN_URL = "https://accounts.pod.ir/oauth2/authorize/?client_id=18060069b4bf248759168bc24000d57c7&response_type=code&redirect_uri=http://www.kidzy.ir/redirect_app&scope=profile social social:write"

    var type = 0

    //argument 0 = default, 1 = PlayPodLogin
    companion object {
        fun newInstance(type: Int = 0): SigninFragment {
            val fragment = SigninFragment()
            val bundle = Bundle()
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }


    val viewModel: SignInViewModel by sharedViewModel()

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

        arguments?.getInt("type", 0)?.let {
            type = it
        }

        toolbar.title = "ورود به کیدزی"

        if(type == 1) {
            toolbar.title = "ورود به پلی پاد"
        }
        if (activity is MainActivity) {
            toolbar.setNavigationIcon(R.drawable.ic_nav_back)
            toolbar.setNavigationOnClickListener {
                this@SigninFragment.dismiss()
            }
        }

        webview.apply {
            settings.javaScriptEnabled = true
            settings.pluginState = WebSettings.PluginState.ON
            settings.allowFileAccess = true
            settings.loadsImagesAutomatically = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            addJavascriptInterface(WebViewJsInterface(), "AndroidTis")
            webChromeClient = WebChromeClient()
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    if (url.startsWith("http://www.kidzy.ir/redirect_app?code=")) {
                        Log.d("token", url)
                        val token = url.substring(url.indexOf("?code=") + 6)
                        viewModel.getUserProfile(token)
                        return true
                    }
                    return false
                }
            }

            val url = when (type) {
                1 -> PLAYPOD_LOGIN_URL
                else -> LOGIN_URL
            }

            loadUrl(url)
        }


        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dismiss()
            }
        })
    }

    internal inner class WebViewJsInterface {
        @JavascriptInterface
        fun postMessage(response: String) {

            when (JSONObject(response).getInt("Type")) {
                1 -> {
                    try {
                        val result = Gson().fromJson(
                            JSONObject(response).getJSONObject("Content").getJSONObject("Result").toString(),
                            PlayPodResponseLogin::class.java
                        )
                        viewModel.repository.playPodProfile = result
                        dismiss()

                    } catch (e: Exception) {
                        e.printStackTrace()
                        dismiss()
                    }
                }
                2 -> {
                    dismiss()
                }
            }
        }
    }
}