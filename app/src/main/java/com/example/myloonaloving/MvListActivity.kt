package com.example.myloonaloving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import com.example.myloonaloving.adapter.MvListAdapter
import com.example.myloonaloving.databinding.ActivityMvListBinding


class MvListActivity : BaseActivity() {
    val bind by binding<ActivityMvListBinding>(R.layout.activity_mv_list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_mv_list)
        Log.d("MvListActivity","Activity Run")
        val mvInfo : MutableList<MusicVideoInfo> = mutableListOf()
        mvInfo.add(MusicVideoInfo("Why Not?","0-snXdhDs1w"))
        mvInfo.add(MusicVideoInfo("Star","zW-AIXAnLcE"))
        mvInfo.add(MusicVideoInfo("Heart Attack","BVVfMFS3mgc"))
        mvInfo.add(MusicVideoInfo("Egoist","UkY8HvgvBJ8"))

        bind.apply {

            mvRecycler.apply {
                adapter = MvListAdapter().apply {
                    mvinfos = mvInfo

                }
                id= View.generateViewId()
            }
           //mvRecycler.itemAnimator=SlideInUpAnimator(OvershootInterpolator(1f))
           Log.d("MvListActivity","Adapter Run")
        }
    }
    //  https://github.com/wasabeef/recyclerview-animators
}