package com.todoware.ejerciciomeli.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.todoware.ejerciciomeli.R
import com.todoware.ejerciciomeli.models.Result
import com.todoware.ejerciciomeli.models.SearchResponse

class ResultRecyclerAdapter(
    var context: Context,
    var searchSearchResponse: SearchResponse
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val rootView =
            LayoutInflater.from(context).inflate(R.layout.item_result_element, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val viewHolder = holder as RecyclerViewViewHolder
        val result: Result = searchSearchResponse.results[position]
        // Picasso is handling the external image fetching
        Picasso.get().load(result.thumbnail).into(viewHolder.imgViewIcon)
        viewHolder.txtViewTitle.text = result.title
        viewHolder.txtViewDescription.text = result.price.toString()
    }

    override fun getItemCount(): Int {
        return searchSearchResponse.results.size
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgViewIcon: ImageView = itemView.findViewById(R.id.item_result_img)
        var txtViewTitle: TextView = itemView.findViewById(R.id.item_result_title)
        var txtViewDescription: TextView = itemView.findViewById(R.id.item_result_description)


    }

}