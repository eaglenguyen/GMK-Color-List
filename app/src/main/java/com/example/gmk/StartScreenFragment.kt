package com.example.gmk


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.gmk.databinding.FragmentStartScreenBinding

import kotlinx.android.synthetic.main.fragment_start_screen.*


class StartScreenFragment : Fragment() {

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!

    // Option menu is displayed with this logic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // layout is on inflated here
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            Toast.makeText(requireContext() ,"WooHoo!", Toast.LENGTH_SHORT).show()
            val action = StartScreenFragmentDirections.actionStartScreenFragmentToColorListFragment()
          //  val intent = Intent(requireContext(), ColorListFragment::class.java)
           // startActivity(intent)
            button.findNavController().navigate(action)
        }
    }

    /**
     * Frees the binding object when the Fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}