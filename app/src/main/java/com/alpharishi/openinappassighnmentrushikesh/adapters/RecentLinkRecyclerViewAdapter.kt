package com.alpharishi.openinappassighnmentrushikesh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpharishi.openinappassighnmentrushikesh.R
import com.alpharishi.openinappassighnmentrushikesh.model.RecentLink
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecentLinkRecyclerViewAdapter(private val linksList: List<RecentLink>) : RecyclerView.Adapter<RecentLinkRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.link_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return linksList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = linksList[position]

        Glide.with(holder.itemView.context)
            .load(currentItem.original_image)
            .apply(RequestOptions().centerCrop())
            .into(holder.ivLink)

        holder.tvLinkName.text = currentItem.app
        holder.tvLinkDate.text = currentItem.times_ago
        holder.tvLinkClickCount.text = currentItem.total_clicks.toString()
        holder.tvLink.text = currentItem.smart_link
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivLink : ImageView = itemView.findViewById(R.id.ivLink)
        val tvLinkName : TextView = itemView.findViewById(R.id.tvLinkName)
        val tvLinkDate : TextView = itemView.findViewById(R.id.tvLinkDate)
        val tvLinkClickCount : TextView = itemView.findViewById(R.id.tvLinkClickCount)
        val tvLink : TextView = itemView.findViewById(R.id.tvLink)
    }

}