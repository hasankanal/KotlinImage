package com.hasankanal.kotlinsearchphotoapp.ui.random

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import com.hasankanal.kotlinsearchphotoapp.utils.Constants
import kotlinx.android.synthetic.main.item_photo.view.*

class RandomImagesAdapter(private val context: Context, val onItemClick: (Photos) -> Unit): RecyclerView.Adapter<RandomImagesAdapter.ViewHolder>() {

    private val list: ArrayList<Photos> = ArrayList<Photos>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val photo: ImageView = view.iv_photo_api
        val user: TextView = view.tv_user_api
        fun bind(model: Photos, onItemClick: (Photos) -> Unit){
            itemView.setOnClickListener { onItemClick(model) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomImagesAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomImagesAdapter.ViewHolder, position: Int) {
        val imageUrl = list.get(position).urls.thumb
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.photo)

        holder.user.text = list.get(position).user.name
        holder.bind(list[position],onItemClick)
        Constants.singlePhotoID = list.get(position).id!!


    }

    override fun getItemCount(): Int = list.size

    fun updateList(data: List<Photos>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}