package com.example.myloonaloving.adapter

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.toColor
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myloonaloving.*
import com.example.myloonaloving.databinding.ItemLyricLineBinding
import com.example.myloonaloving.databinding.ItemMvPlayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.net.URL



val SPECIAL_LETTER="()<>[]"
class ShowLylicAdapter(val temp : (id : String)-> Unit) : RecyclerView.Adapter<ShowLylicAdapter.ViewHolder>(){
    lateinit var lylicdata : LyricInfo
    var lylicLine : MutableList<String> = mutableListOf()
    var lylicTime : MutableList<Int> = mutableListOf()
    var lylicColor : MutableList<Int> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lylicLine[position],position)
    }

    override fun getItemCount(): Int = lylicLine.size


    inner class ViewHolder(private val binding: ItemLyricLineBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {


        @RequiresApi(Build.VERSION_CODES.R)
        @SuppressLint("ResourceAsColor")
        fun bind(src: String, position : Int) {
            binding.singerImageCard.visibility=View.GONE
            binding.groupImageCard.visibility=View.GONE
            var str=src

            Log.d("Onbinding",src)
            MEMBER_NAME.forEach{
                if(src.contains("(${it})"))
                {

                    var sf=it

                    str = str.filterNot{sf.indexOf(it) > -1}
                    str = str.filterNot{ SPECIAL_LETTER.indexOf(it) > -1}
                    if(MEMBER_NAME_MAPPING.containsKey(it)) sf=MEMBER_NAME_MAPPING[it]!!

                    if(sf=="All")
                    {
                        binding.groupImageCard.visibility=View.VISIBLE

                        binding.singerImageCard.visibility=View.GONE
                        Log.d("Showlilic",sf)
                    }
                    else{
                        binding.singerImageCard.visibility=View.VISIBLE
                        binding.groupImageCard.visibility=View.GONE
                        binding.singerImage.setImageDrawable(context.getDrawable(MEMBER_PICTURE[sf]!!))
                    }
                }

            }


            Log.d("ShowLylicAdapter",str)
            binding.textLyricLin.text=str
            if(lylicColor[position]==context.getColor(R.color.black)){
                binding.textLyricLin.setTextColor(context.getColor(R.color.white))
                binding.lylicCard.setCardBackgroundColor(context.getColor(R.color.black))

                binding.lylicCard.y=(context.display!!.width-binding.lylicCard.width).toFloat()
            }
            else{
                binding.textLyricLin.setTextColor(context.getColor(lylicColor[position]))
                binding.lylicCard.setCardBackgroundColor(context.getColor(R.color.white))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
      //  Log.d("initiate","viewholder")
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

