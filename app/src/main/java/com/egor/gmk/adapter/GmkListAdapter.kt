package com.egor.gmk.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.GmkListData
import com.egor.gmk.OnItemClickListener
import com.egor.gmk.databinding.ItemViewBinding


class GmkListAdapter (
    var gmkListData: MutableList<com.egor.gmk.GmkListData>,
    private val listener: com.egor.gmk.OnItemClickListener
        ) : RecyclerView.Adapter<GmkListAdapter.GmkListViewHolder>() {

    // Logic for onclick on the items
    inner class GmkListViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GmkListViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GmkListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GmkListViewHolder, position: Int) {


        holder.binding.apply {
          // position dictates the index of GmkListData array
          // position of companion obj - gmkList
          newImage.setImageResource(gmkListData[position].image)
          newTitle.text = gmkListData[position].title
          newPrice.text = gmkListData[position].price
        }
    }

    override fun getItemCount(): Int {
        return gmkListData.size
    }


}