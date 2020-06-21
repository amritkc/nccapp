package nccstory.ml.androidapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_activity_handbook.*
import kotlinx.android.synthetic.main.pdf3.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("UNREACHABLE_CODE")
class activityhandbook : Fragment() {
    lateinit var mAdView : AdView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_handbook, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        armypfaf1.setOnClickListener {
            val intent = Intent(activity, armypdf::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(activity, navypdf::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(activity, airforcepdf::class.java)
            startActivity(intent)
        }

        ranknavy.setOnClickListener{
            val intent = Intent(activity, navyrank::class.java)
            startActivity(intent)
        }
        rankarmy.setOnClickListener{
            val intent = Intent(activity, armyrank::class.java)
            startActivity(intent)
        }
        rankairforce.setOnClickListener{
            val intent = Intent(activity, airforcerank::class.java)
            startActivity(intent)
        }
        youtubeads.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.youtube.com/channel/UCOreVbjabWSJeqodUqFkq1A")
            startActivity(openURL)
        }

    }
}
