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

class ShowLylicAdapter(val temp : (id : String)-> Unit) : RecyclerView.Adapter<ShowLylicAdapter.ViewHolder>(){
    lateinit var lylicdata : LyricInfo
    var lylicLine : MutableList<String> = mutableListOf()
    var lylicTime : MutableList<Int> = mutableListOf()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lylicLine[position],position)
    }

    override fun getItemCount(): Int = lylicLine.size


    inner class ViewHolder(private val binding: ItemLyricLineBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {


        fun bind(src: String,position : Int) {
            var str=src
            Log.d("Onbinding",src)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        Log.d("initiate","viewholder")
        return ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_lyric_line,
                    parent,
                    false
                ), parent.context
            )

    }


}

