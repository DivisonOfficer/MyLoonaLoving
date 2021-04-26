package com.example.myloonaloving

import android.content.res.AssetManager
import android.content.res.Resources
import java.io.File
import java.io.FileInputStream

class LyricInfo(val name:String ,val location : String) {
    var cnt=0
    var lyricLine : MutableList<String> = mutableListOf()
    var lyricTime : MutableList<Int> = mutableListOf()
    init{

      //  val assetManager = Resource.assets
    //    val inputStream= assetManager.open("posts.json")
     //   val jsonString = inputStream.bufferedReader().use { it.readText() }


    }
}