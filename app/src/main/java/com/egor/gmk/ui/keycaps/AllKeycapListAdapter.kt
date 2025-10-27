package com.egor.gmk.ui.keycaps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.databinding.ItemViewBinding
import com.egor.gmk.room.Colors

class AllKeycapListAdapter : ListAdapter<Colors, AllKeycapListAdapter.KeycapViewHolder>(DiffCallback()) {



    override fun onBindViewHolder(holder: KeycapViewHolder, position: Int) {
        val keycap = getItem(position)

        // Set transition name first
        ViewCompat.setTransitionName(holder.binding.newImage, "keycap_image_transition_$position")

        // Then bind content
        holder.bind(keycap)
    }


    inner class KeycapViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keycap: Colors) {
            // Bind data to the views in the reused layout

            binding.newTitle.text = keycap.title
            binding.newPrice.text = keycap.price
            binding.newImage.setImageResource(keycap.imageRes)

            val roomDbImage = binding.newImage

            // logic for getting position + sharedElement transition
            itemView.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    // linking two views between screens by name.
                    val extras = FragmentNavigatorExtras(
                        roomDbImage to "keycap_image_transition_$pos" // shared element
                    )

                    val action = AllKeycapListFragmentDirections.actionAllKeycapListFragment2ToKeycapsDetailFragment(keycapRoom = keycap, position = pos)
                    itemView.findNavController().navigate(action, extras)
                }


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeycapViewHolder {
        // Inflate the existing layout (item_view.xml)
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeycapViewHolder(binding)
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