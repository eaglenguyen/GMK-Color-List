package com.egor.gmk.ui.keycaps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.databinding.ItemViewBinding
import com.egor.gmk.room.Colors

class AllKeycapListAdapter : ListAdapter<Colors, AllKeycapListAdapter.KeycapViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeycapViewHolder {
        // Inflate the existing layout (item_view.xml)
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeycapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeycapViewHolder, position: Int) {
        val keycap = getItem(position)
        holder.bind(keycap)
    }


    inner class KeycapViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keycap: Colors) {
            // Bind data to the views in the reused layout

            binding.newTitle.text = keycap.title
            binding.newPrice.text = keycap.price
            binding.newImage.setImageResource(keycap.imageRes)

            // Optionally, you can set a click listener here if needed
            itemView.setOnClickListener {
                val action = AllKeycapListFragmentDirections.actionAllKeycapListFragment2ToKeycapsDetailFragment(keycapRoom = keycap)
                itemView.findNavController().navigate(action)
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Colors>() {
        override fun areItemsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem.id == newItem.id // Or some unique field
        }

        override fun areContentsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem == newItem
        }
}


}