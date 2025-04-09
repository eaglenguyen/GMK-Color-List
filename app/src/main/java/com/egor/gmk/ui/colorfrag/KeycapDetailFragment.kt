package com.egor.gmk.ui.colorfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.egor.gmk.databinding.FragmentDetailBinding

class KeycapDetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: KeycapDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keycap = args.keycap
        val keycapRoom = args.keycapRoom


        if (keycap != null) {
            binding.newImages.setImageResource(keycap.image)
            binding.titles.text = keycap.title
            binding.prices.text = keycap.price


            // label aka actionbar title
            (requireActivity() as AppCompatActivity).supportActionBar?.title = keycap.title

        }

        if (keycapRoom != null) {
            binding.newImages.setImageResource(keycapRoom.imageRes)
            binding.titles.text = keycapRoom.title
            binding.prices.text = keycapRoom.price
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}