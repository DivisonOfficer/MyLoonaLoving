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
import java.lang.IllegalStateException
import java.lang.Thread.sleep

class MvYoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    protected inline fun <reified T : ViewDataBinding> binding(resId: Int) : Lazy<T> =
        lazy{ DataBindingUtil.setContentView<T>(this, resId)}
    val bind=binding<ActivityMvYoutubeBinding>(R.layout.activity_mv_youtube)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        YOUTUBE_VIDEO_ID=intent.getStringExtra("YouTubeID")!!
        var lyricPath = intent.getStringExtra("LylicPath")!!
        if(lyricPath!="")
        {
            val lyricInfo = LyricInfo("WhyNot",lyricPath)

            val review :RecyclerView = findViewById(R.id.lylic_recycler_view)
            review.adapter= ShowLylicAdapter().apply{
                this.lylicdata=lyricInfo
                this.lylicLine.addAll(lyricInfo.lyricLine)
                this.lylicTime.addAll(lyricInfo.lyricTime)
                notifyDataSetChanged()
            }
        }
        setContentView(R.layout.activity_mv_youtube)
        val playerView : YouTubePlayerView = findViewById(R.id.mv_youtube_player)
        playerView.initialize(getString(R.string.GoogleApiKey),this)


    }
    var YOUTUBE_VIDEO_ID =""
    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
                                         wasRestored: Boolean) {
        Log.d("MyYoutubeActivity", "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d("MyYoutubeActivity", "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()

        //youTubePlayer?.setPlayerStateChangeListener()
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
            youTubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }

        CoroutineScope(Dispatchers.IO).launch {
            var mili : Int=0
            val tview : TextView = findViewById(R.id.video_current_time)
            while(true) {
                sleep(100)
                try {
                    mili = youTubePlayer?.currentTimeMillis!!
                    Log.d("OnYoutubePlay", "${mili}")
                    runOnUiThread {
                        tview.text = "${mili / 60000}:${(mili % 60000) / 1000}:${mili % 1000}"
                    }
                }catch(e:IllegalStateException)
                {
                    break
                }
            }

        }
    }
    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            Toast.makeText(this@MvYoutubeActivity, "Good, video is playing ok", Toast.LENGTH_SHORT).show()

        }


        override fun onStopped() {
            Toast.makeText(this@MvYoutubeActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@MvYoutubeActivity, "Video has paused", Toast.LENGTH_SHORT).show()
        }
    }
}