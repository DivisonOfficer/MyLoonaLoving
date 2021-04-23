package com.example.myloonaloving

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.viewpager2.widget.ViewPager2
import com.example.myloonaloving.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


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
        bind.mvThumbPager.isUserInputEnabled=false
        CoroutineScope(Dispatchers.IO).launch {
            while(true)
            {
                runOnUiThread {
                    bind.mvThumbPager.setCurrentItem((bind.mvThumbPager.currentItem + 1) % bind.mvThumbPager.adapter!!.itemCount)
                }
                sleep(2000)
            }
        }
    }


    var expand = false
    fun mvExpantion(v: View)
    {

        val addHeight = if(expand) 200 else 0
        expand = !expand
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
        /*ObjectAnimator.ofFloat(bind.mvThumbPager,"ScaleY",2F).apply{
            duration=1000
            start()
        }
        val animation: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                v.layoutParams.height = 400

                v.requestLayout()

            }
        }
        v.startAnimation(animation)*/
        Log.d("mvExpantion","function worked")


    }
}