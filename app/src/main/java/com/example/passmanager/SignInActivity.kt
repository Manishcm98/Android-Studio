package com.example.passmanager

import android.content.Intent
import androidx.biometric.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.passmanager.roomlogin.MyAppDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.concurrent.Executor
import androidx.biometric.BiometricPrompt.AuthenticationCallback

class SignInActivity : AppCompatActivity() {

    companion object{
        var  userSignupdata : MyAppDatabase? = null
    }
    //private lateinit var executor: Executor
    //private lateinit var biometricPrompt: BiometricPrompt


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val MobileNum = et_mobno
        val Mpin = et_pw

        userSignupdata = Room.databaseBuilder<MyAppDatabase>(applicationContext, MyAppDatabase::class.java, "man").allowMainThreadQueries().build()

        val biometricManager1=BiometricManager.from(this)

        when(biometricManager1.canAuthenticate()){

                    BiometricManager.BIOMETRIC_SUCCESS->{

                Log.d("success","yes logged in")
                // i wrote in fragment change it to activity can you send a pic of your code did you add dependency and manifest permission ?
                // i have to import biometric manager RUN not working
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->Log.d("success","does not have a fingerPrint sensor")

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE->Log.d("success","currently unavilable")

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->Log.d("success","do not have any fingerPrint saved")

        }

        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                println("error")
                Toast.makeText(this@SignInActivity,"Authentication Error", Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@SignInActivity,"success", Toast.LENGTH_LONG).show()
                println("succeeded")

                val intnte = Intent(this@SignInActivity,HomeActivity::class.java)
                startActivity(intnte)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                println("failed")

                Toast.makeText(this@SignInActivity,"Authentication Failed", Toast.LENGTH_LONG).show()

            }
        })

        fpsensor.setOnClickListener {
            val promptInfo=BiometricPrompt.PromptInfo.Builder()
                    .setTitle("SIGN IN")
                    .setDescription("Use your fingerprint to login")
                    .setNegativeButtonText("Cancel")
                    .build()

            if (biometricPrompt != null) {
                biometricPrompt.authenticate(promptInfo)
            }
        }

        btn_signin.setOnClickListener {
            val mob : String = MobileNum.text.toString()
            val pin : String = Mpin.text.toString()
            //userSignupdata = Room.databaseBuilder<MyAppDatabase>(applicationContext, MyAppDatabase::class.java, "Pm").allowMainThreadQueries().build()
            val check = userSignupdata!!.myDao().isFavouriteAdded(mob)
            val chk = userSignupdata!!.myDao().isPWAdded(mob)
            if (check == true){
                if (pin == chk) {
                    val intnt = Intent(this,HomeActivity::class.java)
                    intnt.putExtra("posmob",mob)
                    startActivity(intnt)
                }
                else {
                    Toast.makeText(this,"Invalid Password", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this,"SignUp first to get access", Toast.LENGTH_LONG).show()
            }

        }

        sign_up.setOnClickListener {
            val intntwo = Intent(this,SignUpActivity::class.java)
            startActivity(intntwo)
        }

    }

}