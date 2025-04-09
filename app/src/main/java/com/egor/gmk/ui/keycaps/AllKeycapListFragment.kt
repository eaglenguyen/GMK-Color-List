package com.egor.gmk.ui.keycaps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.egor.gmk.R
import com.egor.gmk.data.allKeycaps
import com.egor.gmk.databinding.FragmentGmkListBinding
import com.egor.gmk.databinding.FragmentGmkListRoomBinding
import com.egor.gmk.ui.colorfrag.ColorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllKeycapListFragment: Fragment() {

    private lateinit var keycapAdapter: AllKeycapListAdapter
    private val viewmodel: ColorsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentGmkListRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()




        viewmodel.insertKeycaps(allKeycaps)

        // Combine the red and green lists

        // Setup RecyclerView and Adapter
        val binding = FragmentGmkListRoomBinding.bind(view)
        keycapAdapter = AllKeycapListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = keycapAdapter
        }

        // Search text input visibility logic
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here, Inflates top right menus
                menuInflater.inflate(R.menu.search_menu, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                // Handle the menu selection
                // Logic for menu buttons
                return when (item.itemId) {
                    R.id.search -> {
                        binding.searchInputLayout.visibility =
                            if (binding.searchInputLayout.visibility == View.VISIBLE)
                                View.GONE else View.VISIBLE
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)



        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.allKeycaps.collect{ keycaps ->
                    keycapAdapter.submitList(keycaps)
                }
            }
        }

    }




}