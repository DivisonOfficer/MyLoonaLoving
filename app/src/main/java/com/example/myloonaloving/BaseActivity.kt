package com.example.myloonaloving

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity : AppCompatActivity() {
    protected inline fun <reified T : ViewDataBinding> binding(resId: Int) : Lazy<T> =
        lazy{ DataBindingUtil.setContentView<T>(this, resId)}

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        super.onCreate(savedInstanceState, persistentState)
    }
}