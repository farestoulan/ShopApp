package com.example.Bank.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Bank.R
import com.example.Bank.models.carts.ListOfCartItems

class AdapterCarts(private val mContext: Context, private var data: List<ListOfCartItems?>?) :
    RecyclerView.Adapter<AdapterCarts.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_of_carts, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameOFCarts.setText(data?.get(position)?.product?.name)
        holder.priceOFCarts.setText(data?.get(position)?.product?.price.toString())

        Glide.with(mContext).load(data!![position]?.product?.image).into(holder.imgOFCarts)
    }


    override fun getItemCount(): Int {
        return data!!.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgOFCarts: ImageView
        var nameOFCarts: TextView
        var priceOFCarts: TextView


        init {
            imgOFCarts = itemView.findViewById(R.id.imgOfCarts)
            nameOFCarts = itemView.findViewById(R.id.tvNameOfCarts)
            priceOFCarts = itemView.findViewById(R.id.tvPriceOfCarts)

        }
    }

}