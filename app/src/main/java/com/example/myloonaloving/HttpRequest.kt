package com.example.myloonaloving

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import android.os.Handler

class HttpRequest(val method : String, var uri : String, val handler : Handler) : Thread() {
    companion object{
        val MSG_RESPONSE_FAIL = 100
        val MSG_RESPONSE_SUCCESS = 101

    }

    var paramBuffer : StringBuffer? = null
    override fun run(){
        var response:String = if(method.equals("GET")) get() else post()
        if(response == null || response.isEmpty())
        {
            handler.sendEmptyMessage(MSG_RESPONSE_FAIL)
            return
        }
        handler.obtainMessage(MSG_RESPONSE_SUCCESS,response).sendToTarget()
    }

    fun get():String{
        var response = ""
        paramBuffer?.let{
            uri+=("?"+it.toString())
        }
        val url = URL(uri)
        with(url.openConnection() as HttpURLConnection)
        {
            requestMethod = "GET"
            try{
                BufferedReader(InputStreamReader(inputStream)).use{
                    response = it.readText()
                }
            }catch(e:IOException)
            {
                e.printStackTrace()
            }
        }
        return response
    }
    fun post():String{
        var response = ""
        val url = URL(uri)
        with(url.openConnection() as HttpURLConnection)
        {
            requestMethod = "POST"
            try{
                paramBuffer?.let{
                    val writer = OutputStreamWriter(outputStream)
                    writer.write(it.toString())
                    writer.flush()
                }
                BufferedReader(InputStreamReader(inputStream)).use{
                    response = it.readText()
                }
            }catch(e:IOException)
            {
                e.printStackTrace()
            }
        }
        return response
    }
    fun setParam(key : String, value : String)
    {
        //Template.replace(key,value)
        paramBuffer = paramBuffer?:StringBuffer()
        paramBuffer?.let{
            if(it.length>1) it.append("&")
            it.append("${key}=${value}")
        }
    }
}