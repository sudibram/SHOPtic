package com.example.shoptic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        //        finding ids
        var progressbar=findViewById<ProgressBar>(R.id.progressbar)
        progressbar.visibility= View.GONE
        var phone=findViewById<EditText>(R.id.phone)
        var password=findViewById<EditText>(R.id.password)
        progressbar.visibility=View.GONE
//        button
        var signin=findViewById<Button>(R.id.sign_in)
        signin.setOnClickListener {
            //            implement the signup api here
            progressbar.visibility=View.VISIBLE
//            we are going to use the loopj library to consume our api(make async requests)
            //http(s) requests
            //create the http client
            var client= AsyncHttpClient(80,443)
//          create a body to hold the user info
            var body = JSONObject()
//            PUT the data into the jsonobject body
            body.put("phone",phone.text.toString())
            body.put("password",password.text.toString())

//justpaste.it/ctw73
            val con_body= StringEntity(body.toString())
//            define the http method to use
            client.post(this,
                "https://bramoz.pythonanywhere.com/signin",
                con_body,"application/json",
                object : JsonHttpResponseHandler() {
                    //onsuccess function
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {
//                    check if the status code is 200
                        if (statusCode == 201){
                            Toast.makeText(applicationContext, "Signin Successful"+statusCode,
                                Toast.LENGTH_SHORT).show()
//                        intent to the signin activity
                            val x= Intent(applicationContext,MainActivity::class.java)
                            startActivity(x)
                        }//end of if
                        else{
                            progressbar.visibility=View.GONE
                            Toast.makeText(applicationContext,
                                "Failed. Please try again"+statusCode,
                                Toast.LENGTH_SHORT).show()
                        }
                    } //end of onsuccess

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        throwable: Throwable?,
                        errorResponse: JSONObject?
                    ) {
                        //hide the progressbar
                        progressbar.visibility=View.GONE
                        Toast.makeText(applicationContext,
                            "Something went Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
//        find signup id
        var signup=findViewById<TextView>(R.id.login)
        signup.setOnClickListener {
            var x=Intent(applicationContext,MainActivity2::class.java)
            startActivity(x)
        }

        //postman
    }}


