package com.engineersk.top10downloader

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.ListView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

private const val TAG = "DownloadData"

class DownloadData(private val callback: DownloaderCallback) : AsyncTask<String, Void, String>() {

    interface DownloaderCallback {
        fun onDataAvailable(data: List<FeedEntry>)
        fun onCleared()
    }

    override fun doInBackground(vararg urls: String?): String {
        Log.d(TAG, "doInBackground: starts with ${urls[0]}")
        val rssFeed: String = downloadXML(urls[0])
        if (rssFeed.isEmpty()) {
            Log.e(TAG, "doInBackground: Error Downloading!!!")
        }
        return rssFeed
    }

    override fun onPostExecute(result: String) {
        val parseApplications = ParseApplications()
        if (result.isNotEmpty())
            parseApplications.parse(result)

        callback.onDataAvailable(parseApplications.applications)
    }

    private fun downloadXML(urlPath: String?): String {
        val xmlResult = StringBuilder()

        try {
            val url = URL(urlPath)
            val urlConnection: HttpURLConnection =
                url.openConnection() as HttpURLConnection
            val response = urlConnection.responseCode
            Log.d(TAG, "downloadXML: The response code was $response")

            urlConnection.inputStream.buffered().reader().use {
                xmlResult.append(
                    it.readText()
                )
            }
            Log.d(TAG, "downloadXML: Received ${xmlResult.length} bytes")
            return xmlResult.toString()
        } catch (e: Exception) {
            val errorMessage: String = when (e) {
                is MalformedURLException -> "downloadXML: Invalid URL!!! ${e.message}"
                is IOException -> "downloadXML: IOException Reading data ${e.message}"
                is SecurityException -> {
                    e.printStackTrace()
                    "downloadXML: Security Exception. Needs permission? ${e.message}"
                }
                else -> "downloadXML: Unknown error ${e.message}"
            }
            Log.e(TAG, errorMessage)
        }
        return ""
    }
}