package com.example.shoptic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        var card=findViewById<WebView>(R.id.card)
        card.loadUrl("file:///android_asset/foodcards.html")
        card.getSettings().setJavaScriptEnabled(true);
        card.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }
}