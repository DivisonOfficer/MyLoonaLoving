package com.example.myloonaloving

import android.net.Uri

class MusicVideoInfo(mvideoName:String,myoutubeCode:String) {
    lateinit var videoName:String
    lateinit var youtubeCode : String
    init{
        videoName=mvideoName
        youtubeCode=myoutubeCode
    }
}