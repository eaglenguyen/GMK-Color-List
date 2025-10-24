package com.egor.gmk.ui.keycaps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.egor.gmk.data.allKeycaps
import com.egor.gmk.databinding.FragmentGmkListRoomBinding
import com.egor.gmk.ui.colorfrag.ColorsViewModel
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllKeycapListFragment: Fragment() {

    private lateinit var keycapAdapter: AllKeycapListAdapter
    private val viewmodel: ColorsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val forward = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = 1000 // default is ~300
        }
        val backward = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = 1000
        }

        enterTransition = forward
        returnTransition = backward
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentGmkListRoomBinding.inflate(inflater, container, false)

        // Apply top inset to toolbar
        ViewCompat.setOnApplyWindowInsetsListener(binding.searchInputLayout) { toolbar, insets ->
            val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val layoutParams = toolbar.layoutParams as ConstraintLayout.LayoutParams

            toolbar.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = statusBars.top
            }
            insets
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // inserts latest (if any) keycap list
        viewmodel.insertKeycaps(allKeycaps)
        // Setup RecyclerView and Adapter
        val binding = FragmentGmkListRoomBinding.bind(view)
        keycapAdapter = AllKeycapListAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = keycapAdapter
        }

        // search logic, executes viewmodel once there is text in field
        binding.searchInput.addTextChangedListener { text ->
            val query = text.toString()
            Log.d("InputNew", "Query: $query")
            viewmodel.searchKeycaps(query)
        }

        // code runs in background and gets cancelled when fragment is destroyed, avoiding memory leaks.
        lifecycleScope.launch {
            // collects only when fragment is visible to user (STARTED), will stop once paused/destroyed
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewmodel.searchResults.collect { keycaps ->
                    // giving updated list to adapter , RV also updates its UI
                    keycapAdapter.submitList(keycaps)

                }
            }
        }


    }




}