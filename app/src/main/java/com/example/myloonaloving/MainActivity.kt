package com.example.myloonaloving

import android.graphics.drawable.Drawable
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myloonaloving.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity() {
    val bind by binding<ActivityMainBinding>(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mvThumbs = mutableListOf<Drawable>()
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_butterfly))
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_whynot))
        mvThumbs.add(resources.getDrawable(R.drawable.thumb_mv_favorate))

        bind.mvThumbPager.adapter=MvThumbViewpagerAdapter(mvThumbs)
        bind.mvThumbPager.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        CoroutineScope(Dispatchers.IO).launch {
            while(true)
            {
                runOnUiThread {
                    bind.mvThumbPager.setCurrentItem((bind.mvThumbPager.currentItem+1)%bind.mvThumbPager.adapter!!.itemCount)
                }
                sleep(2000)
            }
        }
    }
}