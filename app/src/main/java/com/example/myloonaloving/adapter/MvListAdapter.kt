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
import com.example.myloonaloving.MusicVideoInfo
import com.example.myloonaloving.R
import com.example.myloonaloving.databinding.ItemMvPlayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.net.URL

class MvListAdapter() : RecyclerView.Adapter<MvListAdapter.ViewHolder>(){
    var mvinfos: MutableList<MusicVideoInfo> = mutableListOf()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        CoroutineScope(Dispatchers.IO).run {
            Log.d(
                "MyListAdapter",
                "${"https://i.ytimg.com/vi/" + mvinfos[position].youtubeCode + "/hq720.jpg"} loading"
            )

            holder.bind(URL("https://i.ytimg.com/vi/" + mvinfos[position].youtubeCode + "/hq720.jpg"), mvinfos[position].videoName)
        }

    }
    private inner class imageDownloader(var imageView : ImageView ) : AsyncTask<URL, Void, Bitmap?>()
    {

        override fun doInBackground(vararg params: URL): Bitmap? {
            //imageView.layoutParams.height=0
            val bitmap:Bitmap? = BitmapFactory.decodeStream(params[0].openStream())


            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
            lateinit var valueAnimator : ValueAnimator
             valueAnimator=ValueAnimator.ofInt(0, 400)
            valueAnimator.duration = 500L
            valueAnimator.addUpdateListener {
                val animatedValue = valueAnimator.animatedValue as Int
                val layoutParams = imageView.layoutParams

                layoutParams.height = animatedValue
                imageView.layoutParams = layoutParams
            }
            valueAnimator.start()
        }
    }

    override fun getItemCount(): Int = mvinfos.size


    inner class ViewHolder(private val binding: ItemMvPlayBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {


        fun bind(image: URL, name:String) {
            binding.isExpand=false
            binding.mvThumb.setOnClickListener {
                binding.isExpand=!binding.isExpand
                lateinit var valueAnimator : ValueAnimator
                if(binding.isExpand)
                {
                    valueAnimator =
                        ValueAnimator.ofInt(0, 200)

                }
                else valueAnimator=ValueAnimator.ofInt(200, 0)
                valueAnimator.duration = 500L
                valueAnimator.addUpdateListener {
                    val animatedValue = valueAnimator.animatedValue as Int
                    val layoutParams = binding.mvLayout.layoutParams

                    layoutParams.height = animatedValue
                    binding.mvLayout.layoutParams = layoutParams
                    binding.mvLayout.alpha=animatedValue.toFloat()/200f
                }
                valueAnimator.start()

            }
            imageDownloader(binding.mvThumb).execute(image)
            binding.mvName.text=name
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
                R.layout.item_mv_play,
                parent,
                false
            ), parent.context
        )



}

