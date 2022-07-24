package com.egor.gmk


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.adapter.ColorAdapter
import com.egor.gmk.databinding.FragmentColorListBinding


class ColorListFragment : Fragment() {

    // Important Variables
    private var _binding: FragmentColorListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true


    // Link for implicit intent in Mainactivity
    companion object {
        const val SEARCH_PREFIX = "https://matrixzj.github.io/docs/gmk-keycaps/"
    }

    // Option menu is displayed with this logic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // @Deprecated("Deprecated in Java")
        setHasOptionsMenu(true)
    }


// layout inflates onCreateView on Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColorListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    // Where recyclerview goes and where you bind specific views to properties by calling findViewById()
    // Below doesnt use findViewById because it uses recyclerview

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.egor.gmk.R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(com.egor.gmk.R.id.action_switch_layout)
        setIcon(layoutButton)
    }


    /**
     * Below is code for layout menu button
     *
     */


    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ColorAdapter(requireContext())
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = ColorAdapter(requireContext())
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(),
                    com.egor.gmk.R.drawable.ic_grid_layout
                )
            else ContextCompat.getDrawable(this.requireContext(),
                com.egor.gmk.R.drawable.ic_linear_layout
            )
    }



    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.egor.gmk.R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            com.egor.gmk.R.id.item_preference -> {
                goToPreferenceFrag()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    private fun goToPreferenceFrag(){
        Toast.makeText(requireContext(), "item is clicked", Toast.LENGTH_SHORT).show()
        val action = ColorListFragmentDirections.actionColorListFragmentToSettingsFragment()
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}