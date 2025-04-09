package com.egor.gmk.ui.gmklist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.OnItemClickListener
import com.egor.gmk.colors.list
import com.egor.gmk.databinding.FragmentGmkListBinding


class GmkListFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentGmkListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    // private val viewModel: ColorsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGmkListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // object created for receiving argument passed in from previous fragment (number = position)
    private val args: GmkListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = args.number

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // populates the color keycaps list via position. Data/list from GmkListFragment
        // Position of companion obj - list

        recyclerView.adapter = GmkListAdapter(list[position], this)


    }




    override fun onItemClick(onClickPosition: Int) {
        // Toast.makeText(requireContext(), "item $position is clicked", Toast.LENGTH_SHORT).show()

        val argPosition = args.number
        val selectedKeycap = list[argPosition][onClickPosition]

        val action = GmkListFragmentDirections.actionGmkListFragmentToKeycapsDetailFragment(keycap = selectedKeycap)
        findNavController().navigate(action)

        // What it was before *
       /* val action1 = GmkListFragmentDirections.actionGmkListFragmentToGreenFragment(
            number = position, image = greenPic[position], title = greenTitle[position], price = greenPrice[position]
        )
        when (item) {
            "green" -> {
                view?.findNavController()?.navigate(action1)
            }
        }
        */
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

