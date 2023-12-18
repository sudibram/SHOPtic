package com.example.shoptic

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
class ItemAdapter(val context:Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    //
    var itemlist: List<Item> = listOf()

    inner class ItemViewHolder(itemView:View)
        :RecyclerView.ViewHolder(itemView){
        //find the views(ids)
        var image_url=itemView.findViewById<ImageView>(R.id.image_url)
        val shop_name=itemView.findViewById<TextView>(R.id.shop_name)
        val minimum_order_amount=itemView.findViewById<TextView>(R.id.minimum_order_amount)
        val availability=itemView.findViewById<TextView>(R.id.availability)
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //define the layout
        //layout inflater
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item,parent,false)
        return ItemViewHolder(itemView)
    }
    //create onBindViewHolder
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentitem=itemlist[position]
        val image=holder.itemView.findViewById<ImageView>(R.id.image_url)
        val room_name=holder.itemView.findViewById<TextView>(R.id.room_name)
        val cost=holder.itemView.findViewById<TextView>(R.id.cost)
        val availability=holder.itemView.findViewById<TextView>(R.id.availability)

        val desc=holder.itemView.findViewById<TextView>(R.id.room_desc)
        desc.visibility=View.GONE
        val id=holder.itemView.findViewById<TextView>(R.id.room_id)
        id.visibility=View.GONE
        val num=holder.itemView.findViewById<TextView>(R.id.square_feet)
        desc.visibility=View.GONE
//        /convert data

        val item=itemlist[position]
        room_name.text=item.shop_name
        cost.text=item.minimum_order_amount
        availability.text=item.availability


        Glide.with(context).load(item.image_url)
            .apply(RequestOptions().centerCrop()).into(image)
//        Glide.with(context)
//            .load(item.image_url)
//            .apply(RequestOptions().centerCrop())
//            .into(image);




        holder.itemView.setOnClickListener {
            desc.text=item.shop_desc
            desc.visibility=View.GONE
            id.text=item.shop_desc
            id.visibility=View.GONE
            num.text=item.location
            num.visibility=View.GONE
            //create the shared preference variavle
            val sharedpref: SharedPreferences =context.getSharedPreferences(
                "file",Context.MODE_PRIVATE)//only accessible in this app
            //define a variable to hold and save the conference room data
            val editor:SharedPreferences.Editor=sharedpref.edit()
            editor.putString("img_url",item.image_url)
            editor.putString("shop_name",item.shop_name)
            editor.putString("availability",item.availability)
            editor.putString("minimum_order_amount",item.minimum_order_amount)
            editor.putInt("shop_id",item.shop_id)
            editor.putString("location",item.location)
            editor.putString("shop_desc",item.shop_desc)

            editor.apply()
            val x= Intent(context, SingleActivity::class.java)
            x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(x)
        }
    }
    override fun getItemCount(): Int {
        return itemlist.size
    }
    //function to set the conference room list
    fun setConferenceItems(itemlist:List<Item>){
        this.itemlist=itemlist
        notifyDataSetChanged()
    }
}