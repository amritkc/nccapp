package nccstory.ml.androidapp

import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.pdf2.*
import kotlinx.android.synthetic.main.startquiz.*

class navypdf:AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(nccstory.ml.androidapp.R.layout.pdf2)

        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

       /* MobileAds.initialize(this, getString(nccstory.ml.androidapp.R.string.addmob_app_id))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(nccstory.ml.androidapp.R.string.adsunit)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd.show()
                super.onAdLoaded()
            }
        }*/



        val pdfView = findViewById<PDFView>(nccstory.ml.androidapp.R.id.pdf_view2)
        pdfView.fromAsset("navyhandbook.pdf").load()

        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adViewstartnavy.loadAd(adRequest)


    }
}