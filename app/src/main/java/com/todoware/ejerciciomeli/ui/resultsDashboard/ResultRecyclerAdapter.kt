package com.todoware.ejerciciomeli.ui.resultsDashboard

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
    var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var searchResponse: SearchResponse? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {

        // Resullt element
        return RecyclerViewViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_result_element, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val viewHolder = holder as RecyclerViewViewHolder
        val result: Result? = searchResponse?.results?.get(position)

        result?.let {
            // Picasso is handling the external image fetching
            Picasso.get().load(result.thumbnail).into(viewHolder.imgViewIcon)
            viewHolder.txtViewTitle.text = result.title
            viewHolder.txtViewDescription.text = result.price.toString()
        }
    }

    override fun getItemCount(): Int {
        return searchResponse?.results?.size ?: 0
    }

    // Set the content
    fun addContent(newResponse: SearchResponse) {
        searchResponse = newResponse
    }

    internal inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgViewIcon: ImageView = itemView.findViewById(R.id.item_result_img)
        var txtViewTitle: TextView = itemView.findViewById(R.id.item_result_title)
        var txtViewDescription: TextView = itemView.findViewById(R.id.item_result_description)


    }

}