import android.graphics.Bitmap
import android.util.LruCache
import android.widget.ImageView

internal class LRUCache {

    private val mMaxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    val mCacheSize = mMaxMemory / 8
    private val TAG = LRUCache::class.java.getSimpleName()


    private var mMemoryCache: LruCache<String?, Bitmap> = object : LruCache<String?, Bitmap>(mCacheSize) {
        override fun sizeOf(key: String?, bitmap: Bitmap): Int {
            return bitmap.getByteCount() * 2 / 1024
        }
    }


    /**
     * @param key is the url of the image
     */

    fun addBitmapToMemoryCache(key: String?, bitmap: Bitmap?) {
        if (getBitmapFromKey(key) == null) {
             mMemoryCache.put(key, bitmap)
        }
    }

    /**
     * The LRU only stores a small number of most recent items in in its cache.
     * So we can just poll the Latest Frame in the memory
     */
    fun removeBitmapFromMemoryCache(key: String?) {
        if (getBitmapFromKey(key) != null) {
            mMemoryCache.remove(key)
        }
    }

    fun getBitmapFromKey(key: String?): Bitmap? {
        return mMemoryCache[key]
    }


    fun evictAll() {
        mMemoryCache.evictAll()
    }


    fun loadBitmap(imageKey: String, imageView: ImageView?): Boolean {

        val mBitmap: Bitmap? = getBitmapFromKey(imageKey)

        return if (mBitmap != null && imageView != null) {
            imageView.setImageBitmap(mBitmap)
            true
        } else {
            false
        }
    }


}

