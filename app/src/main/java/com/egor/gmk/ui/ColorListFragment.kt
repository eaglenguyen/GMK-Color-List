package com.egor.gmk.ui


import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egor.gmk.R
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

        val menuHost: MenuHost = requireActivity()


        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here, Inflates top right menus
                menuInflater.inflate(R.menu.layout_menu, menu)

                // Connects logic of grid menu button to icon id
                val layoutButton = menu.findItem(R.id.action_switch_layout)
                setIcon(layoutButton)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                // Handle the menu selection
                // Logic for menu buttons
                return when (item.itemId) {
                    R.id.action_switch_layout -> {
                        isLinearLayoutManager = !isLinearLayoutManager
                        chooseLayout()
                        setIcon(item)
                        return true
                    }
                    R.id.item_preference -> {
                        goToPreferenceFrag()
                        return true
                    }
                    else -> onMenuItemSelected(item)
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        recyclerView = binding.recyclerView
        chooseLayout()

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
                    R.drawable.ic_linear_layout
                )
            else ContextCompat.getDrawable(this.requireContext(),
                R.drawable.ic_grid_layout
            )
    }


    // Logic for settings button
    private fun goToPreferenceFrag(){
        // Toast.makeText(requireContext(), "item is clicked", Toast.LENGTH_SHORT).show()
        val action = ColorListFragmentDirections.actionColorListFragmentToSettingsFragment()
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}