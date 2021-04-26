package com.example.myloonaloving

import android.content.res.AssetManager
import android.content.res.Resources
import java.io.File
import java.io.FileInputStream

class LyricInfo(val name:String ,val location : String) {
    var cnt=0
    val lyricLine : MutableList<String> = mutableListOf()
    val lyricTime : MutableList<Int> = mutableListOf()
    init{

      //  val assetManager = Resource.assets
    //    val inputStream= assetManager.open("posts.json")
     //   val jsonString = inputStream.bufferedReader().use { it.readText() }

        File("${location}").forEachLine{
            var ctime= it.substring(1,3).toInt()*60000 + it.substring(4,6).toInt()*1000+it.substring(7,9).toInt()*10
            lyricTime.add(ctime)
            lyricLine.add(it.substring(10,))
        }
    }
}