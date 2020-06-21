package nccstory.ml.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.startquiz.*

class learn : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    var url = "http://nccquiz.mygamesonline.org/lesson/index.html"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        supportActionBar!!.title = "Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adlearn.loadAd(adRequest)
        loadweb()

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swiperefreshlearn)
        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            loadweb()
        })

    }
    fun loadweb() {

        val wv: WebView? = findViewById<WebView>(R.id.weblearn)
        progressBar = findViewById<ProgressBar>(R.id.progressBarlearn)
        wv!!.webViewClient = myWebClient()
        wv.settings.javaScriptEnabled = true
        wv.loadUrl(url)

    }
    inner class myWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            progressBar!!.visibility = View.VISIBLE
            view.loadUrl(url)
            return true

        }

        override fun onPageFinished(view: WebView, url: String) {

            super.onPageFinished(view, url)
            progressBar!!.visibility = View.GONE
            swiperefreshlearn.isRefreshing = false



        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            weblearn.loadUrl("file:///android_asset/index.html")
        }
    }
    override fun onBackPressed() {
        if ( weblearn!= null && weblearn.canGoBack()) weblearn.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }


}
