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
import com.example.Bank.models.categories.ListOfCategories

class AdapterCategory(private val mContext: Context, private var data: List<ListOfCategories>) :
    RecyclerView.Adapter<AdapterCategory.MyViewHolder>() {

    private lateinit var mClickListener: ItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_categories, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.setText(data.get(position).name)

        Glide.with(mContext).load(data[position].image).into(holder.path)
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int, data: List<ListOfCategories>)
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        mClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var path: ImageView
        var title: TextView

        override fun onClick(p0: View?) {

            p0?.let { mClickListener.onItemClick(it, position, data) }
        }

        init {
            path = itemView.findViewById(R.id.imgCategories)
            title = itemView.findViewById(R.id.tvCategories)
            itemView.setOnClickListener(this)
        }
    }

    init {
        this.data = data
    }
}