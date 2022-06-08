package com.example.gmk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmk.adapter.NewWordAdapter
import com.example.gmk.colors.*
import com.example.gmk.databinding.FragmentNewWordBinding



class GmkListFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentNewWordBinding? = null
    private lateinit var recyclerView: RecyclerView
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewWordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // Class created for receiving argument passed in from previous fragment (number = position)
    private val args: GmkListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = args.number


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // populates the color keycaps list via position. Data/list from GmkListFragment
        // Position of companion obj - list
        recyclerView.adapter = NewWordAdapter(list[position], this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }



    override fun onItemClick(position: Int) {
        Toast.makeText(requireContext(), "item $position is clicked", Toast.LENGTH_SHORT).show()

        val item = args.word


        val action = GmkListFragmentDirections.actionGmkListFragmentToGreenFragment(number = position, image = greenPic[position], title = greenTitle[position], price = greenPrice[position], info = greenInfo[position])
        val action2 = GmkListFragmentDirections.actionGmkListFragmentToBlueFragment(number2 = position, image2 = bluePic[position], title2 = blueTitle[position], price2 = bluePrice[position])

        if (item == "green") {
            view?.findNavController()?.navigate(action)
        } else {
            view?.findNavController()?.navigate(action2)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

