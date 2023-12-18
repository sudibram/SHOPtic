package com.example.shoptic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class CarouselActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel4)
        var carousel=findViewById<WebView>(R.id.carousel4)
        carousel.loadUrl("file:///android_asset/carousel4.html")
        carousel.getSettings().setJavaScriptEnabled(true);
        carousel.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }
}