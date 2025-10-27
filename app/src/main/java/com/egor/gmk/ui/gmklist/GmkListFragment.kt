package com.egor.gmk.ui.gmklist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.OnItemClickListener
import com.egor.gmk.colors.list
import com.egor.gmk.data.GmkListData
import com.egor.gmk.databinding.FragmentGmkListBinding
import com.google.android.material.transition.MaterialSharedAxis


class GmkListFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentGmkListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forward = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = 1500 // default is ~300
        }
        val backward = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = 300
        }

        enterTransition = forward
        returnTransition = backward
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentGmkListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Apply top inset to toolbar
        ViewCompat.setOnApplyWindowInsetsListener(binding.recyclerView) { rv, insets ->
            val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val layoutParams = rv.layoutParams as ConstraintLayout.LayoutParams

            rv.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = statusBars.top
                bottomMargin = statusBars.bottom
            }

            // padding for bottom contents
            rv.setPadding(
                statusBars.left,
                0,
                statusBars.right,
                150
            )
            insets
        }


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




    override fun onItemClick(position: Int, sharedImage: View, keycap: GmkListData) {
        // Toast.makeText(requireContext(), "item $position is clicked", Toast.LENGTH_SHORT).show()

        val argPosition = args.number
        val selectedKeycap = list[argPosition][position]

        val action = GmkListFragmentDirections.actionGmkListFragmentToKeycapsDetailFragment(keycap = selectedKeycap, position = position)
        val extras = FragmentNavigatorExtras(
            sharedImage to ViewCompat.getTransitionName(sharedImage)!!
        )

        findNavController().navigate(action,extras)

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

