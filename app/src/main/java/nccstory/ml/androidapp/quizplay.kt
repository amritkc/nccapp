package nccstory.ml.androidapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.startquiz.*


class quizplay:AppCompatActivity(){


    //facebook ads here

    private var adView: AdView? = null
    //

    private lateinit var mInterstitialAd: InterstitialAd
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startquiz)
        supportActionBar!!.title = "Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
       adViewstart.loadAd(adRequest)


        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.indusads)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {

                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }
        val myweb:WebView? = findViewById(R.id.quizweb)
        myweb!!.settings.javaScriptEnabled = true
        myweb.webViewClient = myWebClient()
        myweb.loadUrl("http://nccquiz.mygamesonline.org")


        //Facebook ads Code here

       /* adView = AdView(
            this,
            "1431591037005532_1561928570638444",
            AdSize.BANNER_HEIGHT_50
        )
        val adContainer = findViewById(R.id.banner_container) as LinearLayout
        adContainer.addView(adView)
        adView!!.loadAd()
*/

        //Facebook ads code ends here


    }
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
            quizweb.run { quizweb.loadUrl("file:///android_asset/index.html") }

        }
    }

    override fun onBackPressed() {
        if ( quizweb!= null && quizweb.canGoBack()) quizweb.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }
}