package com.egor.gmk.ui.gmklist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.OnItemClickListener
import com.egor.gmk.data.GmkListData
import com.egor.gmk.databinding.ItemViewBinding


class GmkListAdapter (
    private var gmkListData: List<GmkListData>,
    private val listener: OnItemClickListener
        ) : RecyclerView.Adapter<GmkListAdapter.GmkListViewHolder>() {

    // Logic for onclick on the items
    inner class GmkListViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition

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

        //Picasso.get().load()
        val selectedColor = gmkListData[position]

        holder.binding.apply {
          // position dictates the index of GmkListData array
          // position of companion obj - gmkList

          newImage.setImageResource(selectedColor.image)
          newTitle.text = selectedColor.title
          newPrice.text = selectedColor.price
        }



    }

    override fun getItemCount(): Int {
        return gmkListData.size
    }


}