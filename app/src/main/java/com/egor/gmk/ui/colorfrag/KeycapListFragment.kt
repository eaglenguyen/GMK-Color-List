package com.egor.gmk.ui.colorfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.egor.gmk.databinding.FragmentDetailBinding

class KeycapListFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: KeycapListFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val keycap = args.keycap

        binding.newImages.setImageResource(keycap.image)
        binding.titles.text = keycap.title
        binding.prices.text = keycap.price
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}