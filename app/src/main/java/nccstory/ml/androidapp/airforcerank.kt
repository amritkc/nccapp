package nccstory.ml.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_airforcerank.*
import kotlinx.android.synthetic.main.activity_navyrank.*

class airforcerank : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airforcerank)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adrankairforce.loadAd(adRequest)
    }
}
