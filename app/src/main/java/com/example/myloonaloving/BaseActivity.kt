package com.example.myloonaloving

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


val MEMBER_NAME =mutableListOf(
    "희진", "현진" ,"올리비아 혜", "츄","김립","고원","하슬","비비","이브","최리","진솔","여진","ViVi")

abstract class BaseActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(resId: Int) : Lazy<T> =
        lazy{ DataBindingUtil.setContentView<T>(this, resId)}

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        super.onCreate(savedInstanceState, persistentState)
    }
}