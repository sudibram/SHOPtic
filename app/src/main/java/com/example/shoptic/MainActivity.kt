package com.example.shoptic

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(4000)
        installSplashScreen()
        setContentView(R.layout.activity_main)
//        var create=findViewById<CardView>(R.id.food)

//        display the asset file
//        create a webview and find

//            val webView: WebView = findViewById(R.id.webView)
//            webView.settings.javaScriptEnabled = true // Enable JavaScript
//            webView.webChromeClient = WebChromeClient()
//            var find

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://coding.co.ke/lab1_23/shoptic/foodcarousel.html"));
//        startActivity(intent);
        var food=findViewById<CardView>(R.id.food)
        //go to another activity

        food.setOnClickListener {

            intent
            var x=Intent(applicationContext,CarouselActivity::class.java)
            startActivity(x)


        }
//        food.setOnClickListener {
//
//            intent
//            var x=Intent(applicationContext,CardActivity::class.java)
//            startActivity(x)
//
//
//        }

        var parcel=findViewById<CardView>(R.id.parcel)
        //go to another activity

        parcel.setOnClickListener {

            intent
            var x=Intent(applicationContext,CarouselActivity3::class.java)
            startActivity(x)
        }
        var grocery=findViewById<CardView>(R.id.grocery)
        //go to another activity

        grocery.setOnClickListener {

            intent
            var x=Intent(applicationContext,CarouselActivity4::class.java)
            startActivity(x)
        }


        var shop=findViewById<CardView>(R.id.shop)

        shop.setOnClickListener {
            Toast.makeText(applicationContext,"Clicked on shop card",Toast.LENGTH_SHORT).show()
        }
        var pharmacy=findViewById<CardView>(R.id.pharmacy)
        //go to another activity

        pharmacy.setOnClickListener {

            intent
            var x=Intent(applicationContext,CarouselActivity2::class.java)
            startActivity(x)
        }
//        consume getshop api
        var x=Intent(applicationContext,GetShopActivity2::class.java)
        startActivity(x)



        val recyclerView:RecyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val progressbar=findViewById<ProgressBar>(R.id.progressbar)
        adapter= ItemAdapter(applicationContext)
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)

//        define the api to fetch our rooms
//        loopj to consume the api
        val client=AsyncHttpClient(80,443)
//        define the http response method to use
        client.get(this,
            "https://bramoz.pythonanywhere.com/getshops",
            null,"application/json",
            object :JsonHttpResponseHandler(){
                //                onsuccess
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray?
                ) {
                    //convert the jsonarray into a list
                    val gson=GsonBuilder().create()
                    //create the list
                    val list=gson.fromJson(response.toString(),Array<Item>::class.java).toList()
                    //pass the converted list into the adapater
                    adapter.setConferenceItems(list)
                    progressbar.visibility=View.GONE
                }//end of onsuccess
                //onfailure
                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    progressbar.visibility=View.GONE
                    Toast.makeText(applicationContext,
                        "Something Went wrong", Toast.LENGTH_SHORT).show()

                }
            }
        )//end of client get method
        //put the adpater into the recycler view
        recyclerView.adapter=adapter




    }
}