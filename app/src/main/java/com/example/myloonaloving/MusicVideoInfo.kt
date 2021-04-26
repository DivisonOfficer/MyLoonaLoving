package com.example.myloonaloving

import android.net.Uri

class MusicVideoInfo(mvideoName:String,myoutubeCode:String,mlyricPath:String="") {
    lateinit var videoName:String
    lateinit var youtubeCode : String
    lateinit var lyricPath : String
    init{
        videoName=mvideoName
        youtubeCode=myoutubeCode
        lyricPath = mlyricPath
    }
}