package com.example.gmk.colorfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.gmk.R
import com.example.gmk.databinding.FragmentDetailBinding

class BlueFragment : Fragment() {

    private val args: BlueFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val argImg = args.imageBlue
        val argTitle = args.titleBlue
        val argPrice = args.priceBlue

        val images : ImageView = requireView().findViewById(R.id.new_images)
        val title : TextView = requireView().findViewById(R.id.titles)
        val price : TextView = requireView().findViewById(R.id.prices)
        // val description : TextView = requireView().findViewById(R.id.infos)

        images.setImageResource(argImg)
        title.text = argTitle
        price.text = argPrice
        // description.text = "No Thank you"


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}