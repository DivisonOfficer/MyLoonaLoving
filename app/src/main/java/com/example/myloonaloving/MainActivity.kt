package com.example.myloonaloving

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.example.myloonaloving.adapter.AlbumViewpagerAdapter
import com.example.myloonaloving.adapter.MvThumbViewpagerAdapter
import com.example.myloonaloving.databinding.ActivityMainBinding
import com.example.myloonaloving.MusicVideoInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


class MainActivity : BaseActivity() {
    val bind by binding<ActivityMainBinding>(R.layout.activity_main)
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mvThumbs = mutableListOf<Drawable>()
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_butterfly))
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_whynot))
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_favorate))

        val albumThumbs= mutableListOf<Drawable>()
        albumThumbs.add(resources.getDrawable(R.drawable.album_art_1200))
        albumThumbs.add(resources.getDrawable(R.drawable.album_art_shop))
        val songThumbs = mutableListOf<Drawable>()
        songThumbs.add(resources.getDrawable(R.drawable.thumb_singing_1))



        bind.apply {
            mvThumbPager.adapter = MvThumbViewpagerAdapter(mvThumbs)
            mvThumbPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            mvThumbPager.isUserInputEnabled = false

            albumViewpager.adapter = AlbumViewpagerAdapter(albumThumbs)
            albumViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
            albumViewpager.isUserInputEnabled = false
            btMv.setOnClickListener{
                startActivity(Intent(this@MainActivity,MvListActivity::class.java)

                )
            }

            songsViewpager.adapter = AlbumViewpagerAdapter(songThumbs)
            songsViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            songsViewpager.isUserInputEnabled = false

            menuMv.alpha=0F
           // menuMv.translationY=-textviewMv.measuredHeight/2.toFloat()
            textviewAlbum.translationY=-textviewAlbum.measuredHeight/2.toFloat()
            textviewSongs.translationY=-textviewSongs.measuredHeight/2.toFloat()


        }
        CoroutineScope(Dispatchers.IO).launch {
            while(true)
            {

                    runOnUiThread {
                        bind.mvThumbPager.setCurrentItem((bind.mvThumbPager.currentItem + 1) % bind.mvThumbPager.adapter!!.itemCount)
                    }

                if(!expand_mv) sleep(2500)
                sleep(1800)
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            while(true)
            {

                    runOnUiThread {
                        bind.albumViewpager.setCurrentItem((bind.albumViewpager.currentItem + 1) % bind.albumViewpager.adapter!!.itemCount)
                    }

                if(!expand_album) sleep(1250)
                sleep(2100)
            }
        }
        try{
            this@MainActivity.supportActionBar!!.hide()
        }catch (e: NullPointerException)
        {

        }
       // sleep(100)
      //  bind.albumViewpager.layoutParams.height=display!!.width / 2
    }

    var expand_mv = false
    var expand_album = false
    var expand_songs= false
    fun mvExpantion(v: View)
    {
        expand_mv = !expand_mv
        val addHeight = if(expand_mv) 200 else -200

        val valueAnimator =
            ValueAnimator.ofInt(v.measuredHeight, v.measuredHeight + addHeight)
        valueAnimator.duration = 500L
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = v.layoutParams

            layoutParams.height = animatedValue

            v.layoutParams = layoutParams

        }
        valueAnimator.start()


        if(expand_mv) textOpen(bind.menuMv)
        else textOff(bind.menuMv)

        Log.d("mvExpantion", "function worked")
     }
    fun albumExpantion(v: View)
    {
        expand_album = !expand_album
        val addHeight = if(expand_mv) 200 else -200


        if(expand_album) {
           textOpen(bind.albumgoLayout)
            if(expand_songs) textOff(bind.songsgoLayout)
            expand_songs=false

        }
        else textOff(bind.albumgoLayout)
        Log.d("mvExpantion", "function worked")
    }
    fun songsExpantion(v: View)
    {
        expand_songs = !expand_songs


        if(expand_songs) {
            textOpen(bind.songsgoLayout)
            if(expand_album) textOff(bind.albumgoLayout)
            expand_album=false

        }
        else textOff(bind.songsgoLayout)
        Log.d("mvExpantion", "function worked")
    }

    fun textOpen(v: View)
    {
        val valueAnimator =
            ValueAnimator.ofInt(0, 200)
        valueAnimator.duration = 500L
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = v.layoutParams

            layoutParams.height = animatedValue
            v.layoutParams = layoutParams
            v.alpha=animatedValue.toFloat()/200f
        }
        valueAnimator.start()
    }
    fun textOff(v: View)
    {

        val valueAnimator =
            ValueAnimator.ofInt(200, 0)
        valueAnimator.duration = 500L
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = v.layoutParams

            layoutParams.height = animatedValue
            v.layoutParams = layoutParams
            v.alpha=animatedValue.toFloat()/200f
        }
        valueAnimator.start()
    }
}