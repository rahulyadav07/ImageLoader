package com.rahulyadav.imageloaderlibrary


import LRUCache
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class ImageLoader(context: Context) {

    private var imageView: ImageView? = null
    private var context: Context? = null
    private var placeHolder: Int? = null
    private var url: String? = null
    private val lru: LRUCache = LRUCache()

    init {
        this.context = context
    }


    fun load(url: String?) {
        this.url = url
    }

    fun into(imageView: ImageView) {
        this.imageView = imageView
    }


    fun show() {
       val bitmapLoad = lru.loadBitmap(this.url ?: "", this.imageView)

        Log.d("ashish", "show: $bitmapLoad")

        if (!bitmapLoad) {

            downLoadBitMap()
        }
    }

    private fun downLoadBitMap() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL(url)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(input)
                lru.addBitmapToMemoryCache(this@ImageLoader.url, bitmap)
                withContext(Dispatchers.Main) {
                    imageView?.setImageBitmap(bitmap)

                }
            } catch (e: Exception) {
                e.printStackTrace()


            }
        }

    }

}


