package com.example.shoptic

import android.provider.ContactsContract.CommonDataKinds.Phone

data class Item(
    val shop_id:Int,
    val image_url:String,
    val shop_name:String,
    val shop_desc:String,
    val minimum_order_amount:String,
    val availability:String,
    val phone: Phone,
    val location:String
)