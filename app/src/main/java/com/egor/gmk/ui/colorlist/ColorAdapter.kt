package com.egor.gmk.ui.colorlist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.R
import com.egor.gmk.ui.colorlist.ColorListFragmentDirections


class ColorAdapter (context: Context) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    // Important variables
    private val filteredWords: List<String>
    private val colors: List<String>


    init {
        // list of the color words
        filteredWords = context.resources.getStringArray(R.array.color).toList()
        // list of the actual color code
        colors = context.resources.getStringArray(R.array.colorCode).toList()
    }



    // holds the view (button)
    class ColorViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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
        // color name for each button
        val item = filteredWords[position]
        // Color code for each button
        val color = "#" + colors[position]


        holder.buttonView.text = item
        holder.buttonView.setBackgroundColor(Color.parseColor(color))




        holder.buttonView.setOnClickListener {
            val action = ColorListFragmentDirections.actionColorListFragmentToGmkListFragment(
                number = position,
                word = item
            )
            holder.view.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int = filteredWords.size




}



