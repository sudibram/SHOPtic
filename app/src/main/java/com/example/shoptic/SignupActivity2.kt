package com.example.shoptic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceActivity
import android.view.View
import android.widget.*
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

class SignupActivity2: AppCompatActivity() {
    class SignupActivity2 : AppCompatActivity() {
        private lateinit var checkBox: CheckBox

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signup2)

            checkBox = findViewById(R.id.checkbox)

            // Set a listener for the checkbox
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // Checkbox is checked, perform the desired action
                    checkBox.setButtonDrawable(com.google.android.material.R.drawable.ic_mtrl_chip_checked_circle) // Replace with your tick icon
                } else {
                    // Checkbox is unchecked, perform the desired action
                    checkBox.setButtonDrawable(com.google.android.material.R.drawable.ic_mtrl_chip_checked_black) // Replace with your blank checkbox icon
                }
            }


            //////        override fun onCreate(savedInstanceState: Bundle?) {
////            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_signup)
            var firstname=findViewById<EditText>(R.id.firstname)
            var lastname=findViewById<EditText>(R.id.lastname)
            var email =findViewById<EditText>(R.id.Email)
            var phone =findViewById<EditText>(R.id.phone)
            var password =findViewById<EditText>(R.id.password)
            var confirm_password =findViewById<EditText>(R.id.Confirm_password)

//        prgressbar
            var progressbar =findViewById<ProgressBar>(R.id.progressbar)
            progressbar.visibility= View.GONE
//        signup button
            var signup =findViewById<Button>(R.id.signup)
            signup.setOnClickListener {
//            implement the signup api here
                progressbar.visibility= View.VISIBLE
//            we are going to use the loopj library to consume our api(make async requests)
                //http(s) requests
                //create the http client
                var client= AsyncHttpClient(80,443)
//          create a body to hold the user info
                var body = JSONObject()
//            PUT the data into the jsonobject body
                body.put("firstname",firstname.text.toString())
                body.put("lastname",lastname.text.toString())
                body.put("email",email.text.toString())
                body.put("phone",phone.text.toString())
                body.put("password",password.text.toString())
                body.put("confirm_password",confirm_password.text.toString())

                val con_body= StringEntity(body.toString())
//            define the http method to use
                client.post(this,
                    "https://bramoz.pythonanywhere.com/signup",
                    con_body,"application/json",
                    object : JsonHttpResponseHandler() {
                        //onsuccess function
                        override fun onSuccess(
                            statusCode: Int,
                            headers: Array<out Header>?,
                            response: JSONObject?
                        ) {

                        }

                        fun onSuccess(
                            statusCode: Int,
                            headers: Array<out PreferenceActivity.Header>?,
                            response: JSONObject?
                        ) {
//                    check if the status code is 200
                            if (statusCode == 200){
                                Toast.makeText(applicationContext, "Signup Successful"+statusCode,
                                    Toast.LENGTH_SHORT).show()
//                        intent to the signin activity
                                val x= Intent(applicationContext,MainActivity::class.java)
                                startActivity(x)
                            }//end of if
                            else{
                                progressbar.visibility= View.GONE
                                Toast.makeText(applicationContext,
                                    "Failed. Please try again"+statusCode,
                                    Toast.LENGTH_SHORT).show()
                            }
                        } //end of onsuccess

                        override fun onFailure(
                            statusCode: Int,
                            headers: Array<out Header>?,
                            throwable: Throwable?,
                            errorResponse: JSONArray?
                        ) {
                            //hide the progressbar
                            progressbar.visibility= View.GONE
                            Toast.makeText(applicationContext,
                                "Something went Wrong", Toast.LENGTH_SHORT).show()
                        }




                    }
                )
            }

            var signin=findViewById<TextView>(R.id.login)
            signin.setOnClickListener {
                var x= Intent(applicationContext,MainActivity::class.java)
                startActivity(x)
            }

//create a dabasabe called shoptic

        }
    }}




