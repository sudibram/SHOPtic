package com.example.shoptic

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject
import com.bumptech.glide.request.RequestOptions

class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

                //get the sharedpref file
                val sharedpref: SharedPreferences =getSharedPreferences(
                    "file", Context.MODE_PRIVATE)
                //access the ids
                //get image data from the sharedpref
                val image=sharedpref.getString("img_url","")
                var imageview=findViewById<ImageView>(R.id.image_url)
                Glide.with(applicationContext).load(image )
                    .apply(com.bumptech.glide.request.RequestOptions().centerCrop()).into(imageview)

                val id=sharedpref.getInt("shop_id",0)
                var shop_id=findViewById<TextView>(R.id.shop_id)
                shop_id.text=id.toString()

                val sq=sharedpref.getString("location","")
                var location=findViewById<TextView>(R.id.location)
                location.text=sq

                val desc=sharedpref.getString("shop_desc","")
                var shop_des=findViewById<TextView>(R.id.shop_desc)
                shop_des.text=desc


                val name=sharedpref.getString("shop_name","")
                var shop_name=findViewById<TextView>(R.id.shop_name)
                shop_name.text=name


                val order_cost=sharedpref.getString("\"minimum_order_amount","")
                var minimum_order_amount=findViewById<TextView>(R.id.minimum_order_amount)
                minimum_order_amount.text=order_cost

                val status=sharedpref.getString("availability","")
                var availability=findViewById<TextView>(R.id.availability)
                availability.text=status
                //mpesa payment
                val progress = findViewById<ProgressBar>(R.id.progressbar)
                progress.visibility= View.GONE
                val phone: EditText =findViewById(R.id.phone)

                val order: Button =findViewById(R.id.order)

                order.setOnClickListener {
                    progress.visibility=View.VISIBLE
//            loopj-library to consume the api(http requests)
//            cretae http client
                    val client= AsyncHttpClient(true,80,443)
//            body that holds the data requeired in the api(request)
                    var body= JSONObject()
                    //put the data provided by user into the body
                    body.put("phone",phone.text.toString())
                    body.put("amount",minimum_order_amount.text.toString())
                    val con_body= StringEntity(body.toString())
//            define the http method to use
                    client.post(this,
                        "https://bramoz.pythonanywhere.com/mpesa_payment",
                        con_body,"application/json",
                        object : JsonHttpResponseHandler() {
                            //onsuccess function
                            override fun onSuccess(
                                statusCode: Int,
                                headers: Array<out Header>?,
                                response: JSONObject?
                            ) {
//                    check if the status code is 200
                                if (statusCode == 204){
                                    Toast.makeText(applicationContext,
                                        "Please check your phone and complete the transaction"+statusCode,
                                        Toast.LENGTH_SHORT).show()
                                    progress.visibility=View.GONE
                                }//end of if
                                else{
                                    progress.visibility=View.GONE
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
                                progress.visibility=View.GONE
                                Toast.makeText(applicationContext,
                                    "Something went Wrong"+statusCode, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }

