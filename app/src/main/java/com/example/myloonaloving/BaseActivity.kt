package com.example.myloonaloving

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


val MEMBER_NAME =mutableListOf(
    "희진", "현진" ,"올리비아 혜", "츄","김립","고원","하슬","비비","이브","최리","진솔","여진","ViVi","all","ALL","All","올리비아혜","AII")
val MEMBER_NAME_MAPPING=mapOf("올리비아 혜" to "올리비아혜",
"all" to "All", "ALL" to "All", "AII" to "All", "ViVi" to "비비","올혜" to "올리비아혜", "vivi" to "비비")

val MEMBER_PICTURE=mapOf("희진" to R.drawable.loona_heejin,
"현진"  to R.drawable.loona_hyunjin,
"올리비아혜" to   R.drawable.loona_olivia,
"츄"  to R.drawable.loona_chuu,
"김립"  to R.drawable.loona_kimrip,
"고원" to  R.drawable.loona_gowon,
"하슬" to R.drawable.loona_haseul,
"비비" to R.drawable.loona_vivi,
"이브"  to R.drawable.loona_yves,
"최리"  to R.drawable.loona_choerry,
"진솔"  to R.drawable.loona_jinsol,
"여진" to R.drawable.loona_yeojin)

val MEMBER_COLOR=mapOf("희진" to R.color.deep_pink,
    "현진"  to R.color.yellow,
    "올리비아혜" to   R.color.silverstar,
    "츄"  to R.color.peach,
    "김립"  to R.color.red,
    "고원" to  R.color.edengreen,
    "하슬" to R.color.green,
    "비비" to R.color.light_pink,
    "이브"  to R.color.vurgundy,
    "최리"  to R.color.purple,
    "진솔"  to R.color.blue,
    "여진" to R.color.orange)

abstract class BaseActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(resId: Int) : Lazy<T> =
        lazy{ DataBindingUtil.setContentView<T>(this, resId)}

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        super.onCreate(savedInstanceState, persistentState)
    }
}