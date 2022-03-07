package com.example.gmk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.gmk.Colors
import com.example.gmk.R


class ColorAdapter (private val dataset: String, context: Context) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private val filteredWords: List<String>

    init {
        val words = context.resources.getStringArray(R.array.colorr).toList()

        filteredWords = words.sorted()
    }



    // holds the view (button)
    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buttonView: Button = view.findViewById(R.id.button_item)
    }

    // inflates/displays the view( button )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_view, parent, false)


        return ColorViewHolder(adapterLayout)


    }
    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = filteredWords[position]

        //val context = holder.view.context

        holder.buttonView.text = item


        // holder.buttonView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int = filteredWords.size


}



























/**

class ColorAdapter (private val dataset: List<Colors>, private val context: Context) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    // holds the view (button)
    class ColorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val buttonView: Button = view.findViewById(R.id.button_item)
    }

    // inflates/displays the view( button )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_view, parent, false)


        return ColorViewHolder(adapterLayout)


    }
    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = dataset[position]
        holder.buttonView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
        return dataset.size

    }
}

 */

