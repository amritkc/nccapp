package nccstory.ml.androidapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_aboutapp.*


class aboutapp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutapp)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        MobileAds.initialize(this, getString(R.string.addmob_app_id))
        val adRequest = AdRequest.Builder().build()
        adabout.loadAd(adRequest)


        // intent to open instagram
        instagramclick.setOnClickListener {
            val uri: Uri = Uri.parse("http://instagram.com/_u/nccstory")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.instagram.android")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/nccstory")
                    )
                )
            }

       }//over



        //facebook intent


        facebookclick.setOnClickListener {
            val uri: Uri = Uri.parse("http://facebook.com/nccstoryml")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.facebook.katana")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://facebook.com/nccstoryml")
                    )
                )
            }

        }//over


        //twitter
        twitterclick.setOnClickListener {
            val uri: Uri = Uri.parse("http://twitter.com/nccstory")
            val likeIng = Intent(Intent.ACTION_VIEW, uri)

            likeIng.setPackage("com.twitter.android")

            try {
                startActivity(likeIng)
            } catch (e: ActivityNotFoundException) {

            }

        }//over



        //priv

        aboutpriv.setOnClickListener {
            val url = "https://nccstories.blogspot.com/p/privacy-policy-for-ncc-story-at-ncc.html"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))


        }

        //terms and condition

        abouttem.setOnClickListener {
            val url = "https://nccstories.blogspot.com/p/terms-and-conditions-welcome-to-ncc.html"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))

        }



    }
}


