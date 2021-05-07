package com.example.myloonaloving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myloonaloving.adapter.ShowLylicAdapter
import com.example.myloonaloving.databinding.ActivityMvYoutubeBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStreamReader
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.lang.Thread.sleep

class MvYoutubeActivity : BaseActivity() {

    val bind by binding<ActivityMvYoutubeBinding>(R.layout.activity_mv_youtube)
    lateinit var mAdapter : ShowLylicAdapter
    var lyricPath = ""
    var onLyricScroll = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mv_youtube)
        YOUTUBE_VIDEO_ID=intent.getStringExtra("YouTubeID")!!
        bind.youTubePlayerView.play(YOUTUBE_VIDEO_ID)
        lyricPath = intent.getStringExtra("LylicPath")!!


        if(lyricPath!="")
        {

            val lyricInfo = LyricInfo("WhyNot",lyricPath)

            InputStreamReader(resources.assets.open(lyricPath)).forEachLine{
                if(lyricInfo.timeAdjust==-1) lyricInfo.timeAdjust = it.toInt()
                else {
                    var ctime = it.substring(1, 3).toInt() * 60000 + it.substring(4, 6)
                        .toInt() * 1000 + it.substring(7, 9).toInt() * 10
                    lyricInfo.lyricTime.add(ctime)
                    lyricInfo.lyricLine.add(it.substring(10,))
                }
            }
            Log.d("LyricLinecnt","${lyricInfo.lyricLine.size}")

            val review :RecyclerView = findViewById(R.id.lylic_recycler_view)
            mAdapter = ShowLylicAdapter(temp=
            {

            }
            ).apply{
                this.lylicdata=lyricInfo
                this.lylicLine=convertLylicFormat(lyricInfo.lyricLine)
                this.lylicTime=lyricInfo.lyricTime
                this.lylicColor=lilycColorGenerate(lyricInfo.lyricLine)
                bind.lylicRecyclerView.adapter=this
                Log.d("MyYoutubeActivity","AfterGenerateAdapter ${this.itemCount}")
               notifyDataSetChanged()
            }

        }



        bind.lylicRecyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            onLyricScroll=50
        }
      //  val playerView : YouTubePlayerView = findViewById(R.id.mv_youtube_player)
       // playerView.initialize(getString(R.string.GoogleApiKey),this)
        var playTime=0
        CoroutineScope(Dispatchers.IO).launch {
            while(true) {
                try {
                    playTime = bind.youTubePlayerView.getCurrentPlayTime()!!


                    if (playTime > 0) {
                        if(onLyricScroll==0) {
                            var line = 0
                            for (i in 0..mAdapter.itemCount - 1) {
                                line = i
                                //    Log.d("time on ${mAdapter.lylicTime[i]}",mAdapter.lylicLine[i])
                                if (playTime < mAdapter.lylicTime[i]) break
                            }
                            runOnUiThread {
                                bind.lylicRecyclerView.scrollToPosition(line)
                            }
                            Log.d("Playing On", "${playTime} / ${line}th line")
                        }
                        else onLyricScroll--
                    }


                } catch (e: NullPointerException) {
                    Log.d("YouTubePlayer", "Null")
                }
                catch(e:IllegalStateException){
                    finish()
                }

                sleep(50)
            }
        }
    }
    var YOUTUBE_VIDEO_ID =""
    fun lilycColorGenerate(lylic : MutableList<String>) : MutableList<Int>{
        val ret = mutableListOf<Int>()
        var cc=R.color.black
        var pos=0
        lylic.forEach{
            val str= it
            cc=-1
            MEMBER_NAME.forEach{
                if(str.contains("(${it})"))
                {
                    var name=it
                    if(MEMBER_NAME_MAPPING.containsKey(name)) name= MEMBER_NAME_MAPPING[it]!!
                    if(name=="All") cc=R.color.black
                    else cc=MEMBER_COLOR[name]!!
                }
            }
            if(cc==-1)
            {
                if(pos==0) cc=R.color.white
                else cc=ret[pos-1]
            }

            ret.add(cc)
            pos++
        }
        return ret
    }
    fun convertLylicFormat(lines:MutableList<String>, singer:String = "") : MutableList<String>
    {
        val otherB="<{["
        val otherE=">}]"
        if(singer!="")
        {
            lines[0] = "(${singer}) ${lines[0]}"
            return lines
        }
        var pos=0

        lines.forEach{

            otherB.forEach{
                lines[pos].replace(it,'(')
            }
            otherE.forEach{
                lines[pos].replace(it,')')
            }

            pos++
        }
        return lines
    }
}