package nccstory.ml.androidapp


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)


    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myWebView: WebView?
        myWebView = view.findViewById(R.id.webView2)
        myWebView.settings.javaScriptEnabled = true
        webView2.webViewClient = myWebClient()
        webView2.loadUrl("https://nccstories.blogspot.com/p/products.html")
        webView2.canGoBack()
        webView2.goBack()
        /*progressBar4.visibility = View.VISIBLE
        Timer("settingup",false).schedule(6000){
            progressBar4.visibility = View.INVISIBLE
        }*/

    }
    @Suppress("OverridingDeprecatedMember")
    inner class myWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            view.loadUrl(url)
            return true

        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)


        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            webView2.run { webView2.loadUrl("file:///android_asset/index.html") }

        }
    }


}