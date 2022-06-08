package com.example.gmk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gmk.NewWordData
import com.example.gmk.OnItemClickListener
import com.example.gmk.R
import kotlinx.android.synthetic.main.item_view.view.*

class NewWordAdapter (
    var newWordData: MutableList<NewWordData>,
    private val listener: OnItemClickListener
        ) : RecyclerView.Adapter<NewWordAdapter.NewWordViewHolder>() {


    // Logic for onclick on the items
    inner class NewWordViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener{

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewWordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view,parent,false)

        return NewWordViewHolder(view)
    }


    override fun onBindViewHolder(holder: NewWordViewHolder, position: Int) {

        holder.itemView.apply {
          // position dictates the index of NewWordData array
          // position of companion obj - newWordList
          new_image.setImageResource(newWordData[position].image)
          new_text.text = newWordData[position].title
          new_text_2.text = newWordData[position].price
        }
    }

    override fun getItemCount(): Int {
        return newWordData.size
    }


}