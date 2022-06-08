package com.example.gmk


import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmk.adapter.ColorAdapter
import com.example.gmk.databinding.FragmentColorListBinding


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
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
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}