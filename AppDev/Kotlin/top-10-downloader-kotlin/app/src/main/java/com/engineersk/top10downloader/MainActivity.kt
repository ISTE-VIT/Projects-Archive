package com.engineersk.top10downloader

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.engineersk.top10downloader.viewmodels.EMPTY_FEED_LIST
import com.engineersk.top10downloader.viewmodels.FeedViewModel

class FeedEntry {
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var summary: String = ""
    var imageURL: String = ""
    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            release = $releaseDate
            imageURL = $imageURL
        """.trimIndent()
    }

}

private const val TAG = "MainActivity"
private const val STATE_URL = "feedUrl"
private const val STATE_LIMIT = "feedLimit"

class MainActivity : AppCompatActivity() {
    private lateinit var xmlListView: ListView
    private val feedViewModel by lazy { ViewModelProvider(this)[FeedViewModel::class.java] }

    private var feedURL: String =
        "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
    private var feedLimit: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: called...")

        xmlListView = findViewById(R.id.xmlListView)
        val feedAdapter = FeedAdapter(this, R.layout.list_record, EMPTY_FEED_LIST)
        xmlListView.adapter = feedAdapter

        if (savedInstanceState != null) {
            feedURL = savedInstanceState.getString(STATE_URL)!!
            feedLimit = savedInstanceState.getInt(STATE_LIMIT)
        }

        feedViewModel.feedEntries.observe(this, Observer { feedEntries ->
            feedAdapter.setFeedList(feedEntries ?: EMPTY_FEED_LIST)
        })

        feedViewModel.downloadUrl(feedURL.format(feedLimit))
        Log.d(TAG, "onCreate: Done!!!")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)
        if (feedLimit == 10) {
            menu?.findItem(R.id.menu_top_10)?.isChecked = true
        } else
            menu?.findItem(R.id.menu_top_25)?.isChecked = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_free -> feedURL =
                "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml"
            R.id.menu_paid -> feedURL =
                "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml"
            R.id.menu_songs -> feedURL =
                "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
            R.id.menu_refresh -> feedViewModel.onCleared()
            R.id.menu_top_10, R.id.menu_top_25 -> {
                if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 35 - feedLimit
                    Log.d(
                        TAG,
                        "onOptionsItemSelected: ${item.title} setting feedLimit to $feedLimit"
                    )
                } else {
                    Log.d(
                        TAG, "onOptionsItemSelected: ${item.title} setting feedLimit unchanged..."
                    )
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }
        feedViewModel.downloadUrl(feedURL.format(feedLimit))
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_URL, feedURL)
        outState.putInt(STATE_LIMIT, feedLimit)
    }

}