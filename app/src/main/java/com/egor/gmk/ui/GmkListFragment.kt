package com.egor.gmk.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.OnItemClickListener
import com.egor.gmk.colors.*
import com.egor.gmk.data.additionPic
import com.egor.gmk.data.additionPrice
import com.egor.gmk.data.additionTitle
import com.egor.gmk.data.blackPic
import com.egor.gmk.data.blackPrice
import com.egor.gmk.data.blackTitle
import com.egor.gmk.data.bluePic
import com.egor.gmk.data.bluePrice
import com.egor.gmk.data.blueTitle
import com.egor.gmk.data.brownPic
import com.egor.gmk.data.brownPrice
import com.egor.gmk.data.brownTitle
import com.egor.gmk.data.greenPic
import com.egor.gmk.data.greenPrice
import com.egor.gmk.data.greenTitle
import com.egor.gmk.data.greyPic
import com.egor.gmk.data.greyPrice
import com.egor.gmk.data.greyTitle
import com.egor.gmk.data.multiPic
import com.egor.gmk.data.multiPrice
import com.egor.gmk.data.multiTitle
import com.egor.gmk.data.orangePic
import com.egor.gmk.data.orangePrice
import com.egor.gmk.data.orangeTitle
import com.egor.gmk.data.pinkPic
import com.egor.gmk.data.pinkPrice
import com.egor.gmk.data.pinkTitle
import com.egor.gmk.data.purpPic
import com.egor.gmk.data.purpPrice
import com.egor.gmk.data.purpTitle
import com.egor.gmk.data.redPic
import com.egor.gmk.data.redPrice
import com.egor.gmk.data.redTitle
import com.egor.gmk.data.whitePic
import com.egor.gmk.data.whitePrice
import com.egor.gmk.data.whiteTitle
import com.egor.gmk.data.yellowPic
import com.egor.gmk.data.yellowPrice
import com.egor.gmk.data.yellowTitle
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




    override fun onItemClick(position: Int) {
        // Toast.makeText(requireContext(), "item $position is clicked", Toast.LENGTH_SHORT).show()

        val item = args.word
        val positionNew = args.number
        val selectedKeycap = list[positionNew][position]

        val action = GmkListFragmentDirections.actionGmkListFragmentToKeycapsDetailFragment(keycap = selectedKeycap)
         findNavController().navigate(action)


    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

