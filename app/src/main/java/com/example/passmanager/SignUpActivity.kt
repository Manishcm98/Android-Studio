package com.example.passmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.passmanager.SignInActivity.Companion.userSignupdata
import com.example.passmanager.roomlogin.UserInfo
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val MobileNum = et_mobnoup
        val Mpin = et_pwup
        val ReMpin = et_repwup

        btn_signinup.setOnClickListener {
            val mob : String = MobileNum.text.toString()
            val pin : String = Mpin.text.toString()
            val rpin : String = ReMpin.text.toString()
            if (rpin == pin) {
                val newUser = UserInfo(mob,rpin)
                SignInActivity.userSignupdata!!.myDao().addUser(newUser)
                val intnt = Intent(this,SignInActivity::class.java)
                startActivity(intnt)
                Toast.makeText(this,"sign up success", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this,"Mpin did not match", Toast.LENGTH_LONG).show()
            }
        }

        sign_inup.setOnClickListener {
            val intntwo = Intent(this,SignInActivity::class.java)
            startActivity(intntwo)
        }
    }
}