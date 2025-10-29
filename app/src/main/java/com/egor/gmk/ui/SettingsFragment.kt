package com.egor.gmk.ui

import android.os.Bundle
import android.view.View

import androidx.preference.PreferenceFragmentCompat
import com.egor.gmk.R
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // when called , this inflates <root_pref> XML and attaches it to a hidden RecyclerView aka listView.
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(requireContext().getColor(android.R.color.darker_gray))

        // Access the internal RecyclerView
        val recyclerView = listView

        // Add padding to top or bottom
        val topPadding = resources.getDimensionPixelSize(R.dimen.preference_padding_top)
        val bottomPadding = resources.getDimensionPixelSize(R.dimen.preference_padding_bottom)

        recyclerView.setPadding(
            recyclerView.paddingLeft,
            topPadding,
            recyclerView.paddingRight,
            bottomPadding
        )

        // So the content can scroll under the toolbar/nav bar smoothly
        recyclerView.clipToPadding = false
    }

}