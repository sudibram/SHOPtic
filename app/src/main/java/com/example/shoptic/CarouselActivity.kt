package com.example.shoptic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button

class CarouselActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel)
        var carousel=findViewById<WebView>(R.id.carousel)
        carousel.loadUrl("file:///android_asset/carousel.html")
        carousel.getSettings().setJavaScriptEnabled(true);
        carousel.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        var card=findViewById<WebView>(R.id.card)
        card.loadUrl("file:///android_asset/foodcards.html")
        card.getSettings().setJavaScriptEnabled(true);
        card.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

    }
}