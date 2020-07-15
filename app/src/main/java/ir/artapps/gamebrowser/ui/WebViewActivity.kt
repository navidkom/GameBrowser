package ir.artapps.gamebrowser.ui

import android.app.Activity
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import ir.artapps.gamebrowser.R


class WebViewActivity : Activity() {
    private var mWebView: WebView? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)


        // html file with sample swf video in sdcard

        //swf2.html points to swf in sdcard
        mWebView = findViewById<View>(R.id.webview) as WebView
        mWebView!!.webViewClient = SSLTolerentWebViewClient()
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.settings.pluginState = WebSettings.PluginState.ON
        mWebView!!.settings.allowFileAccess = true
        mWebView!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        mWebView!!.loadUrl(intent.getStringExtra("url"))
        //        mWebView.loadUrl("http://fe.bmqb.com/tower_game/index.html");

//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            System.exit(4);
//        } else {
//            mWebView.loadUrl("file://" + Environment.getExternalStorageDirectory() + "/swf2.html");
//        }
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
}