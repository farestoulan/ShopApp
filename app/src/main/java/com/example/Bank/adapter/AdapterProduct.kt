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
import com.example.Bank.models.products.ListOfProduct

class AdapterProduct (private val mContext: Context, private var data: List<ListOfProduct?>?) :
    RecyclerView.Adapter<AdapterProduct.MyViewHolder>() {

    private lateinit var mClickListener: ItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_product, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = data!![position]
        obj.let {
            holder.name.text = data!![position]?.name
        holder.price.text = data!![position]?.price

        Glide.with(mContext).load(data!![position]?.image).into(holder.path)
    }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int, data: List<ListOfProduct?>?)
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        mClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return data!!.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var path: ImageView
        var name: TextView
        var price :TextView

        override fun onClick(p0: View?) {

            p0?.let { mClickListener.onItemClick(it, position, data!!) }
        }

        init {
            path = itemView.findViewById(R.id.imgViewProduct)
            name = itemView.findViewById(R.id.tvNameProducts)
            price = itemView.findViewById(R.id.tvPriceProduct)
            itemView.setOnClickListener(this)
        }
    }

    init {
        this.data = data
    }

}