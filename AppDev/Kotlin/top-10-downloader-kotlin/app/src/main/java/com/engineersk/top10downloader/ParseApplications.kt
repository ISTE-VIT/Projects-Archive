package com.engineersk.top10downloader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory

class ParseApplications {
    private val TAG = "ParseApplications"
    val applications: ArrayList<FeedEntry> = ArrayList()

    fun parse(xmlData: String): Boolean {
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(xmlData.reader())
            var eventType = xmlPullParser.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xmlPullParser.name?.toString()?.toLowerCase()
                when(eventType){
                    XmlPullParser.START_TAG -> {
//                        Log.d(TAG, "parse: String tag for $tagName")
                        if(tagName == "entry")
                            inEntry = true

                    }
                    XmlPullParser.TEXT -> textValue = xmlPullParser.text
                    XmlPullParser.END_TAG -> {
//                        Log.d(TAG, "parse: ending tag for $tagName")
                        if(inEntry){
                            when(tagName){
                                "entry"->{
                                    applications.add(currentRecord)
                                    currentRecord = FeedEntry()
                                    inEntry = false
                                }
                                "name"->currentRecord.name = textValue
                                "artist"->currentRecord.artist = textValue
                                "releasedate"->currentRecord.releaseDate = textValue
                                "summary"->currentRecord.summary = textValue
                                "image"->currentRecord.imageURL = textValue
                            }
                        }
                    }
                }

                eventType = xmlPullParser.next()

            }

//            for (app in applications){
//                Log.d(TAG, "***********************")
//                Log.d(TAG, app.toString())
//            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
            status = false
        }

        return status
    }
}