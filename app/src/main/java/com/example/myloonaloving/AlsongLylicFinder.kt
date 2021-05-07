package com.example.myloonaloving

import android.app.DownloadManager
import android.net.Uri
import androidx.core.net.toUri
import java.net.URL

class AlsongLylicFinder(var artist : String , var songName : String) {
    val serverUrl = "http://lyrics.alsong.co.kr/alsongwebservice/server1.asmx".toUri()
    fun getLyrics()
    {
        DownloadManager.Request(serverUrl).addRequestHeader("content-type","application/soap+xml")
    }
}