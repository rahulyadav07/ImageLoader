package com.rahulyadav.imageloader


import android.content.Context
import androidx.startup.Initializer
import com.rahulyadav.imageloaderlibrary.ImageLoader

/**
 * For loading the image into image you need three things
 *
 * @param context ->
 *
 * @param -> url
 *
 * @param ->
 */
class ImageLoaderInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        appContext = context
    }

    override fun dependencies() = emptyList<Class<Initializer<*>>>()

    companion object{
        private lateinit var appContext: Context
        private val instance: ImageLoader by lazy {
            ImageLoader(appContext)
        }
        fun get() = instance


    }


}