package com.example.gmk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.gmk.databinding.FragmentDetailBinding

class BlueFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: BlueFragmentArgs by navArgs()


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


        val argImg = args.image2
        val argTitle = args.title2
        val argPrice = args.price2

        val images : ImageView = requireView().findViewById(R.id.new_images)
        val title : TextView = requireView().findViewById(R.id.textView)
        val price : TextView = requireView().findViewById(R.id.textView2)
        val description : TextView = requireView().findViewById(R.id.textView3)

        images.setImageResource(argImg)
        title.text = argTitle
        price.text = argPrice
        description.text = "No Thank you"


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}