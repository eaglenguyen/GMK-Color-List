package com.egor.gmk.ui.colorfrag

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.egor.gmk.R
import com.egor.gmk.databinding.FragmentDetailBinding


class GreenFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // Class created for receiving argument passed in from previous fragment (number = position)
    private val args: GreenFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        val argImg = args.image
        val argTitle = args.title
        val argPrice = args.price
        // val argInfo = args.info

        val images : ImageView = requireView().findViewById(R.id.new_images)
        val title : TextView = requireView().findViewById(R.id.titles)
        val price : TextView = requireView().findViewById(R.id.prices)
        // val description : TextView = requireView().findViewById(R.id.infos)



        images.setImageResource(argImg)
        title.text = argTitle
        price.text = argPrice
        // description.text = argInfo


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}