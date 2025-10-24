package com.egor.gmk.ui.colorfrag

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.egor.gmk.R
import com.egor.gmk.databinding.FragmentDetailBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis

class KeycapDetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: KeycapDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform(requireContext(), true).apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 500
            scrimColor = Color.TRANSPARENT
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
        }

        sharedElementReturnTransition = MaterialContainerTransform(requireContext(), false).apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 400
            scrimColor = Color.TRANSPARENT
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
        }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)



        // Apply top inset to toolbar
        ViewCompat.setOnApplyWindowInsetsListener(binding.newImages) { toolbar, insets ->
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

        val keycap = args.keycap
        val keycapRoom = args.keycapRoom
        val position = args.position


        if (keycap != null) {
            binding.newImages.setImageResource(keycap.image)
            binding.titles.text = keycap.title
            binding.prices.text = keycap.price


            // label aka actionbar title
            (requireActivity() as AppCompatActivity).supportActionBar?.title = keycap.title

            ViewCompat.setTransitionName(binding.newImages, "gmk_image_transition_$position")

        }


        if (keycapRoom != null) {
            binding.newImages.setImageResource(keycapRoom.imageRes)
            binding.titles.text = keycapRoom.title
            binding.prices.text = keycapRoom.price
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}