package com.example.myloonaloving.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.myloonaloving.MusicVideoInfo
import com.example.myloonaloving.R

class MyListAdapter(private val mvinfos: MutableList<MusicVideoInfo>) : RecyclerView.Adapter<MyListAdapter.PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_mv_play, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(("https://i.ytimg.com/vi/"+mvinfos[position].youtubeCode+"/hq720.jpg").toUri(), mvinfos[position].videoName)
    }

    override fun getItemCount(): Int = mvinfos.size
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.mv_thumb)
        private val textView: TextView = itemView.findViewById(R.id.mv_name)
        fun bind(image: Uri, name:String) {
            imageView.setImageURI(image)
            textView.text=name
        }
    }
}

