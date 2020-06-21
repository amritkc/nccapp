package nccstory.ml.androidapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import kotlinx.android.synthetic.main.songview.*

class songview:AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd
    private val SPLASH_TIME_OUT:Long = 30000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songview)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))

        val adLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd { ad : UnifiedNativeAd ->
                    // Show the ad.
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(errorCode: Int) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build()





        /*  Handler().postDelayed({
              // This method will be executed once the timer is over
              // Start your app main activity

              mInterstitialAd = InterstitialAd(this)
              mInterstitialAd.adUnitId = getString(R.string.indusads)
              mInterstitialAd.loadAd(AdRequest.Builder().build())
              mInterstitialAd.adListener = object : AdListener() {
                  override fun onAdLoaded() {

                      mInterstitialAd.show()
                      super.onAdLoaded()

                  }
              }

              // close this activity
          }, SPLASH_TIME_OUT)


  */

        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView1.loadAd(adRequest)

    }
}