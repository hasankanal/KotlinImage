package com.hasankanal.kotlinsearchphotoapp.ui.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhoto
import com.hasankanal.kotlinsearchphotoapp.utils.Constants
import kotlinx.android.synthetic.main.item_photo.view.*
import kotlin.collections.ArrayList

class ProfileAdapter(private val context: Context, val onItemClick: (CapturePhoto) -> Unit): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private val list: ArrayList<CapturePhoto> = ArrayList<CapturePhoto>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val photo: ImageView = view.iv_photo_api
        val user: TextView = view.tv_user_api
        fun bind(model: CapturePhoto, onItemClick: (CapturePhoto) -> Unit){
            itemView.setOnClickListener { onItemClick(model) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        val imageUrl = list.get(position).savePhoto
        val bitmap = imageUrl!!.toBitmap()
        holder.photo.setImageBitmap(bitmap)
        holder.user.text = list.get(position).currentUser
        holder.bind(list[position],onItemClick)
        Constants.singlePhotoID = list.get(position).savePhoto!!


    }

    override fun getItemCount(): Int = list.size

    fun updateList(data: List<CapturePhoto>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun String.toBitmap(): Bitmap?{
        Base64.decode(this,Base64.DEFAULT).apply {
            return BitmapFactory.decodeByteArray(this,0,size)
        }
    }
}