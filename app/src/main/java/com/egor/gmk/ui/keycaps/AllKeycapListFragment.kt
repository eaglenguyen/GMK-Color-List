package com.egor.gmk.ui.keycaps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
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
import com.egor.gmk.ui.colorfrag.SearchScreenUiEvent
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



        //viewmodel.insertKeycaps(allKeycaps)


        // Setup RecyclerView and Adapter
        val binding = FragmentGmkListRoomBinding.bind(view)
        keycapAdapter = AllKeycapListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = keycapAdapter
        }

        binding.searchInput.addTextChangedListener { text ->
            val query = text.toString()
            Log.d("InputNew", "Query: $query")
            viewmodel.searchKeycaps(query)
        }










        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.searchResults.collect { keycaps ->
                    keycapAdapter.submitList(keycaps)

                }
            }
        }


    }




}