package nccstory.ml.androidapp



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_tags.*

/**
 * A simple [Fragment] subclass.
 */
class tagsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        // create ContextThemeWrapper from the original Activity Context with the custom theme
//        // create ContextThemeWrapper from the original Activity Context with the custom theme
//        val contextThemeWrapper: Context =
//            ContextThemeWrapper(activity, android.R.style.Theme_Light_NoTitleBar_Fullscreen) //AppCompat.Light.NoActionBar)
//
//        // clone the inflater using the ContextThemeWrapper
//        // clone the inflater using the ContextThemeWrapper
//        val inflater = inflater.cloneInContext(contextThemeWrapper)
//
//
//
//


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quiz.setOnClickListener {
            val intent = Intent(activity, quizplay::class.java)
            startActivity(intent)
        }
        learn.setOnClickListener {
            val intent = Intent(activity, nccstory.ml.androidapp.learn::class.java)
            startActivity(intent)
        }

    }

}
