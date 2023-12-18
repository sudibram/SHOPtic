package com.example.shoptic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //configure the window to full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAGS_CHANGED
        )
        //define the function to control the display period
        Handler().postDelayed({
//           create intent to mainactivity activity after splash screen
            val x= Intent(applicationContext,MainActivity2::class.java)
            startActivity(x)
            finish()
        },2000)
    }
}