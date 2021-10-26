package com.engineersk.top10downloader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textview.MaterialTextView

class ViewHolder(view: View) {
    val tvName: MaterialTextView = view.findViewById(R.id.tvName)
    val tvArtist: MaterialTextView = view.findViewById(R.id.tvArtist)
    val tvSummary: MaterialTextView = view.findViewById(R.id.tvSummary)
}

class FeedAdapter(
    context: Context, private val resource: Int,
    private var applications: List<FeedEntry?>) : ArrayAdapter<FeedEntry>(context, resource) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return applications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View
        if (convertView != null) {
            view = convertView
            viewHolder = view.tag as ViewHolder
        } else {
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }

        val currentApplication = applications[position]

        viewHolder.tvName.text = currentApplication?.name
        viewHolder.tvArtist.text = currentApplication?.artist
        viewHolder.tvSummary.text = currentApplication?.summary

        return view
    }

    fun setFeedList(feedList: List<FeedEntry?>) {
        this.applications = feedList
        notifyDataSetChanged()
    }
}