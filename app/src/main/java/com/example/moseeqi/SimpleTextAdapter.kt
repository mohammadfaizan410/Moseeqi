package com.example.moseeqi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleTextAdapter(private val data: List<String>) : RecyclerView.Adapter<SimpleTextAdapter.TextViewHolder>() {

    // ViewHolder class that holds the references to the views for each item
    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(android.R.id.text1)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return TextViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        // Get element from your dataset at this position
        // Replace the contents of the view with that element
        holder.textView.text = data[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
