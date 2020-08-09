package ir.artapps.gamebrowser.ui

import android.app.Activity
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import ir.artapps.gamebrowser.R
import kotlinx.android.synthetic.main.login_fragment.*


class WebViewActivity : Activity() {
    private var mWebView: WebView? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)


        if (savedInstanceState == null) {


            Log.d("WebviewUrl", intent.getStringExtra("url"))
            // html file with sample swf video in sdcard

            //swf2.html points to swf in sdcard
            mWebView = findViewById<View>(R.id.webview) as WebView
            mWebView!!.webViewClient = SSLTolerentWebViewClient()
            mWebView!!.settings.javaScriptEnabled = true
            mWebView!!.settings.pluginState = WebSettings.PluginState.ON
            mWebView!!.settings.allowFileAccess = true
            mWebView!!.settings.loadsImagesAutomatically = true
            mWebView!!.settings.domStorageEnabled = true
            mWebView!!.settings.databaseEnabled = true
            mWebView!!.webChromeClient = WebChromeClient()
            mWebView!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            mWebView!!.loadUrl(intent.getStringExtra("url"))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        mWebView!!.destroy()
        finish()
    }

    internal class SSLTolerentWebViewClient : WebViewClient() {
        override fun onReceivedSslError(
            view: WebView,
            handler: SslErrorHandler,
            error: SslError
        ) {
            handler.proceed() // Ignore SSL certificate errors
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        webview.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        webview.restoreState(savedInstanceState)
    }
}