package nccstory.ml.androidapp


import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val mAuth = FirebaseAuth.getInstance()
    var progressBar: ProgressBar? = null
    internal var url = "https://nccstories.blogspot.com"
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    var countries:MutableList<String> = ArrayList()
    var displayList:MutableList<String> = ArrayList()
 private lateinit var mInterstitialAd: InterstitialAd
//    private val SPLASH_TIME_OUT:Long = 60000 // 1 min
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.home ->{
                replaceFragment(homeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.Products ->{
                replaceFragment(ProductsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.Tags ->{
                replaceFragment(tagsFragment())
                return@OnNavigationItemSelectedListener  true
            }
            R.id.web ->{
                replaceFragment(webFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.hand ->{
                replaceFragment(activityhandbook())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
     @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         //header Drwableeee

         val navigationView = findViewById(R.id.nav_view) as NavigationView
         navigationView.setNavigationItemSelectedListener(this)
         val headerview = navigationView.getHeaderView(0)
        val  txtview_edit = headerview.findViewById<LinearLayout>(R.id.hQuiz) as LinearLayout

         txtview_edit.setOnClickListener(View.OnClickListener {
             val intent = Intent(this, quizplay::class.java)
             startActivity(intent)
         })

         val openinsta = headerview.findViewById<LinearLayout>(R.id.hInstagram) as LinearLayout
         openinsta.setOnClickListener {
             Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()

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

         }

         //over drawnle header

         toolbar = findViewById(R.id.toolbar)
         setSupportActionBar(toolbar)

         drawerLayout = findViewById(R.id.drawer_layout)
         navView = findViewById(R.id.nav_view)

         val toggle = ActionBarDrawerToggle(
             this, drawerLayout, toolbar, 0, 0
         )
         drawerLayout.addDrawerListener(toggle)
         toggle.syncState()
         navView.setNavigationItemSelectedListener(this)














// end start

        //Adsmob Ads InduAds
//




           MobileAds.initialize(this, getString(R.string.addmob_app_id))





//
//
//        Handler().postDelayed({
//            // This method will be executed once the timer is over
//            // Start your app main activity
//

         //end stop


         //start

//
           mInterstitialAd = InterstitialAd(this)
       mInterstitialAd.adUnitId = getString(R.string.indusads)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
     mInterstitialAd.adListener = object : AdListener() {
           override fun onAdLoaded() {
//
           mInterstitialAd.show()
               super.onAdLoaded()

              }
          }

        val swipe = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipe.setOnRefreshListener(OnRefreshListener {
            Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
            loadweb()
        })



        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(homeFragment())
        loadweb()
         //end

         //nav

         //






    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Quiz", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, quizplay::class.java)
                startActivity(intent)
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Mail", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    data = Uri.parse("mailto:")
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, "nccstoryml@mail.com")
                    putExtra(Intent.EXTRA_SUBJECT, "nccstoryml@mail.com")
                }
                    intent.setPackage("com.google.android.gm")
                    startActivity(intent)

            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()

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

            }
            R.id.aboutappmenu -> {
                Toast.makeText(this, "About App", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, aboutapp::class.java)
                startActivity(intent)

            }
            R.id.shareapp -> {
                Toast.makeText(this, "Share App", Toast.LENGTH_SHORT).show()
                val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, " Download NccStory Android App                   " +
                    "                                               " +
                    "  With Handbook Pre-built  " +
                    "                                                                                       " +
                    "  NCC QUIZ " +
                    "                                                                                         " +
                    "  NCC STORY " +
                    "                                                                                  " +
                    "Tips and Tricks to Complete RDC,TSC, NSC,VSC" +
                    "                                                                                    " +
                    " https://play.google.com/store/apps/details?id=nccstory.ml.androidapp")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent ,"Select the App:"))
            }
            R.id.logout ->{
                mAuth.signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
//from here

    fun loadweb() {

        var wv: WebView?

        wv = findViewById<WebView>(R.id.webview)
        progressBar = findViewById<ProgressBar>(R.id.progressBar3)
        wv.webViewClient = myWebClient()
        wv.settings.javaScriptEnabled = true
        wv.settings.builtInZoomControls = true
        wv.settings.displayZoomControls = true
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
            swiperefresh.setRefreshing(false)


        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            webview.run { webview.loadUrl("file:///android_asset/index.html") }

        }
    }

    override fun onBackPressed() {
        if ( webview!= null && webview.canGoBack()) webview.goBack() // if there is previous page open it
        else super.onBackPressed() //if there is no previous page, close app
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      val id = item.getItemId()
    if(id == R.id.notify) {
            val intent = Intent(this,notification::class.java)
            startActivity(intent)
            return true
    }
    return true
    }



//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.getItemId()
//        if (id == R.id.aboutappmenu) {
//            Toast.makeText(this, "About App", Toast.LENGTH_LONG).show()
//                val intent = Intent(this, aboutapp::class.java)
//                startActivity(intent)
//            return true
//        } else if(id == R.id.shareapp){
//            Toast.makeText(this, "Share App", Toast.LENGTH_LONG).show()
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.putExtra(Intent.EXTRA_TEXT, " Download NccStory Android App                   " +
//                    "                                               " +
//                    "  With Handbook Pre-built  " +
//                    "                                                                                       " +
//                    "  NCC QUIZ " +
//                    "                                                                                         " +
//                    "  NCC STORY " +
//                    "                                                                                  " +
//                    "Tips and Tricks to Complete RDC,TSC, NSC,VSC" +
//                    "                                                                                    " +
//                    " https://play.google.com/store/apps/details?id=nccstory.ml.androidapp")
//            intent.type = "text/plain"
//            startActivity(Intent.createChooser(intent ,"Select the App:"))
//            return true
//        }else if(id == R.id.notify){
//            val intent = Intent(this,notification::class.java)
//            startActivity(intent)
//            return true
//    } else if(id == R.id.search){
//
//            return true
//        }
//
//
//        return super.onOptionsItemSelected(item)
//    }


    private fun replaceFragment(Fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentCointainer,Fragment)
        fragmentTransaction.commit()
    }

}



