package com.example.shoptic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class CarouselActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel3)
        var carousel=findViewById<WebView>(R.id.carousel3)
        carousel.loadUrl("file:///android_asset/carousel3.html")
        carousel.getSettings().setJavaScriptEnabled(true);
        carousel.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }
}