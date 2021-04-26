package com.example.passmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splash_image : ImageView = findViewById(R.id.splash)
        splash_image.alpha = 0f
        splash_image.animate().setDuration(1500).alpha(1f).withEndAction {
            val it = Intent(this,SignInActivity::class.java)
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}