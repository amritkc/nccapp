package nccstory.ml.androidapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import nccstory.ml.androidapp.R.color.colorAccent

class Login : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    val   TAG = "login"
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (mAuth.currentUser != null){
            MainPage()
        }
        //signup
        eSignup.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
        //Google Login
//        google_button.setOnClickListener {
//            googleLogin()
//        }
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(this, gso)


//        configureGoogleSignIn()
//        setupUI()
//

        //Email Log in
        eLogin.setOnClickListener {
            eLoginprogress.visibility = View.VISIBLE
            eLogin.isEnabled = false
            val email = eEmail.text.toString()
            val password = ePassword.text.toString()
            if(!email.isEmpty() && !password.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){Task ->
                    if(Task.isSuccessful){
                        MainPage()
                    }else{
                        eLoginprogress.visibility = View.GONE
                        eLogin.isEnabled = true
                        Toast.makeText(this, "Please Enter Valid Email Password", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                eLoginprogress.visibility = View.GONE
                eLogin.isEnabled = true
                Toast.makeText(this, "Email or Password Can't be blank", Toast.LENGTH_SHORT).show()
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
//    private  fun googleLogin(){
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }

    //google sigin
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e)
//                // ...
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    MainPage()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    // ...
////                    Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
////                    updateUI(null)
//                }
//
//                // ...
//            }
//    }





//
//
//    private fun configureGoogleSignIn() {
//        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
//    }
//    private fun setupUI() {
//        google_button.setOnClickListener {
//            signIn()
//        }
//    }
//    private fun signIn() {
//        val signInIntent: Intent = mGoogleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                firebaseAuthWithGoogle(account)
//            } catch (e: ApiException) {
//                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        mAuth.signInWithCredential(credential).addOnCompleteListener {
//            if (it.isSuccessful) {
//                MainPage()
//            } else {
//                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
//            }
//        }
//    }



}