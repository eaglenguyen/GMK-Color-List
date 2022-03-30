package com.example.gmk


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmk.adapter.WordAdapter
import com.example.gmk.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {

    companion object {
        const val SEARCH_PREFIX = "https://matrixzj.github.io/docs/gmk-keycaps/"
    }



    private var _binding: FragmentWordListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

   // private var numberId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       // arguments?.let {
       //     numberId = it.getInt(NUMBER)
      //  }



    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }
    // Class created for this receiving destination
    private val args: WordListFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Retrieves argument from original destination/adapter via args
        val position = args.number

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(position, requireContext())

        // Adds a [DividerItemDecoration] between items
       // recyclerView.addItemDecoration(
       //     DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
       // )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}