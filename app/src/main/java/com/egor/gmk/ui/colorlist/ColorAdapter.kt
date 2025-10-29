package com.egor.gmk.ui.colorlist

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.R
import com.egor.gmk.ui.colorlist.ColorListFragmentDirections
import androidx.core.graphics.toColorInt
import androidx.core.view.setPadding


class ColorAdapter (context: Context) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    // list of the color words
    private val filteredWords: List<String> = context.resources.getStringArray(R.array.color).toList()
    // list of the actual color code
    private val colors: List<String> = context.resources.getStringArray(R.array.colorCode).toList()


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
        val item = filteredWords[position]
        val button = holder.buttonView

        button.text = item

        if (position == 12) { // e.g., 3 or whatever index
            // Rainbow gradient for special button
            val rainbowDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.RED,
                    "#FF7F00".toColorInt(), // Orange
                    Color.YELLOW,
                    Color.GREEN,
                    Color.BLUE,
                    "#4B0082".toColorInt(), // Indigo
                    "#8B00FF".toColorInt()  // Violet
                )
            ).apply {
                cornerRadius = 100f
            }

            button.background = rainbowDrawable
            button.backgroundTintList = null // Remove tint so gradient shows

        } else {
            // Solid color for regular buttons
            val color = "#" + colors[position]
            holder.buttonView.setBackgroundColor(color.toColorInt())
        }



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



