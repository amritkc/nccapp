package nccstory.ml.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splashscreen.*

class splashscreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 2000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        iv_note.alpha= 0f
        iv_note.animate().setDuration(500).alpha(1f).withEndAction{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }



//        Handler().postDelayed({
//            // This method will be executed once the timer is over
//            // Start your app main activity
//
//            startActivity(Intent(this,Login::class.java))
//
//            // close this activity
//            finish()
//        }, SPLASH_TIME_OUT)


    }
}
