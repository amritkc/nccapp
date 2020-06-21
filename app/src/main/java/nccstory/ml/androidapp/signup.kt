package nccstory.ml.androidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        sTerms2.setOnClickListener {
            val url = "https://nccstories.blogspot.com/p/terms-and-conditions-welcome-to-ncc.html"
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }
        sSignup.setOnClickListener {
            sSignup.isEnabled = false
            val email = sEmail.text.toString()
            val username = sUsername.text.toString()
            val password1 = sPassword1.text.toString()
            val password2 = sPassword2.text.toString()
            //error handling
            if (!email.isEmpty() && !username.isEmpty() && !password1.isEmpty() && !password2.isEmpty()){
                if (password1 == password2){
                    if (password1.length >= 6){

                        mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this){Task ->
                            if (Task.isSuccessful){
                                val user = mAuth.currentUser
                                user!!.sendEmailVerification().addOnCompleteListener(this) {Task ->
                                    if (Task.isSuccessful){
                                        Toast.makeText(this, "Verification sent to "+email, Toast.LENGTH_SHORT).show()
                                        MainPage()
                                    }else{
                                        Toast.makeText(this, "verification not sent", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }else{
                                sSignup.isEnabled = true
                                Toast.makeText(this, "Failed Try Again", Toast.LENGTH_SHORT).show()
                            }
                        }


                    }else{
                        sSignup.isEnabled = true
                        Toast.makeText(this, "Password Length must be more than 6", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    sSignup.isEnabled = true
                    Toast.makeText(this, "Password didn't match!", Toast.LENGTH_SHORT).show()
                }
            }else{
                sSignup.isEnabled = true
                Toast.makeText(this, "Please fill all the blank", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private  fun MainPage(){
        val user = mAuth.currentUser
        val userid = user!!.uid
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}