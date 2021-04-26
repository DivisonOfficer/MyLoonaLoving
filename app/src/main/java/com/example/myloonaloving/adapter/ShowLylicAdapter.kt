package com.example.myloonaloving.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myloonaloving.LyricInfo
import com.example.myloonaloving.MEMBER_NAME
import com.example.myloonaloving.MusicVideoInfo
import com.example.myloonaloving.R
import com.example.myloonaloving.databinding.ItemLyricLineBinding
import com.example.myloonaloving.databinding.ItemMvPlayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.net.URL

class ShowLylicAdapter() : RecyclerView.Adapter<ShowLylicAdapter.ViewHolder>(){
    lateinit var lylicdata : LyricInfo
    val lylicLine : MutableList<String> = mutableListOf()
    val lylicTime : MutableList<Int> = mutableListOf()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        CoroutineScope(Dispatchers.IO).run {
           // Log.d(                "MyListAdapter",                "${"https://i.ytimg.com/vi/" + mvinfos[position].youtubeCode + "/hq720.jpg"} loading"            )

            holder.bind(lylicLine[position])
        }

    }

    override fun getItemCount(): Int = lylicLine.size


    inner class ViewHolder(private val binding: ItemLyricLineBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {


        fun bind(src: String) {
            var str=src
            MEMBER_NAME.forEach{
                if(src.contains(it))
                {
                    binding.singerImage.visibility=View.VISIBLE
                    str= src.toMutableList().remove(it.toMutableList()).toString()
                    str = src.toMutableList().removeAll(listOf('(',')','[',']')).toString()

                }
                binding.textLyricLin.text=str
            }


        }
    }
    fun add(info:MusicVideoInfo, position:Int){
        notifyItemInserted(position)
    }
    fun remove(position : Int)
    {
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =

        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_lyric_line,
                parent,
                false
            ), parent.context
        )



}

