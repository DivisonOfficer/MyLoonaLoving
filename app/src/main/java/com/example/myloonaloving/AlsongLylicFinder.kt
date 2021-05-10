package com.example.myloonaloving

import android.app.DownloadManager
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.core.net.toUri
import java.net.URL

class AlsongLylicFinder(var artist : String , var songName : String) {
    val serverUrl = "http://lyrics.alsong.co.kr/alsongwebservice/service1.asmx"

    var Template : String = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<SOAP-ENV:Envelope\n xmlns:SOAP-ENV=\"http://www.w3.org/2003/05/soap-envelope\"\n    xmlns:SOAP-ENC=\"http://www.w3.org/2003/05/soap-encoding\"\n    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\nxmlns:ns2=\"ALSongWebServer/Service1Soap\"\nxmlns:ns1=\"ALSongWebServer\"\nxmlns:ns3=\"ALSongWebServer/Service1Soap12\">\n<SOAP-ENV:Body><ns1:GetResembleLyric2>\n<ns1:stQuery>\n<ns1:strTitle>{title}</ns1:strTitle>\n<ns1:strArtistName>{artist_name}</ns1:strArtistName>\n<ns1:nCurPage>{page}</ns1:nCurPage>\n</ns1:stQuery>\n</ns1:GetResembleLyric2>\n</SOAP-ENV:Body>\n</SOAP-ENV:Envelope>"

    val handler = Handler(){
        when(it.what)
        {
            HttpRequest.MSG_RESPONSE_FAIL -> {
                Log.d("AlsongLylicFinder","Failed to Connect Server")
            }
            HttpRequest.MSG_RESPONSE_SUCCESS ->{
                httpResponse=it.obj as String
                Log.d("AlsongLylicFinder",httpResponse)
            }
        }
        true
    }
    //var targetTextView : TextView? = null
    var httpResponse : String = ""

   init {
       val httpRequest = HttpRequest("POST",serverUrl,handler)
      // httpRequest.setParam("data",Template.replace("title",songName).replace("artist_name",artist).replace("page","0"))
    //   httpRequest.setParam("headers","{\'content-type\':\'application/soap+xml\'}")
       httpRequest.setParam("artist_name",artist)

       httpRequest.setParam("title",songName)
httpRequest.setParam("page","0")
       httpRequest.start()
   }


}