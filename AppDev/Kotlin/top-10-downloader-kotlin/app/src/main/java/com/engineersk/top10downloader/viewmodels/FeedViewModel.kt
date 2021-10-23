package com.engineersk.top10downloader.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.engineersk.top10downloader.DownloadData
import com.engineersk.top10downloader.FeedEntry
import java.util.*

private const val TAG = "FeedViewModel"
val EMPTY_FEED_LIST: List<FeedEntry> = Collections.emptyList()

class FeedViewModel : ViewModel(), DownloadData.DownloaderCallback {

    private var downloadData: DownloadData? = null
    private var feedCachedUrl = "INVALIDATED"

    private val feeds = MutableLiveData<List<FeedEntry?>>()
    val feedEntries: LiveData<List<FeedEntry?>>
        get() {
            return feeds
        }

    init {
        feeds.postValue(EMPTY_FEED_LIST)
    }

    fun downloadUrl(feedURL: String) {
        Log.d(TAG, "downloadUrl: Starting Async Task...")
        if(feedURL != feedCachedUrl) {
            downloadData = DownloadData(this)
            downloadData?.execute(feedURL)
            feedCachedUrl = feedURL
            Log.d(TAG, "downloadUrl: Done!!!")
        }else{
            Log.d(TAG, "downloadUrl - URL not changed!!!")
        }
    }

    fun invalidate(){
        feedCachedUrl = "INVALIDATED"
    }
    override fun onDataAvailable(data: List<FeedEntry>) {
        Log.d(TAG, "onDataAvailable: called...")
        feeds.value = data
        Log.d(TAG, "onDataAvailable: ends...")
    }

    override fun onCleared(){
        Log.d(TAG, "onCleared: called...")
        downloadData?.cancel(true)
    }
}