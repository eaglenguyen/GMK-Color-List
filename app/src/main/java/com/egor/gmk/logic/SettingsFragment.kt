package com.egor.gmk.logic

import android.os.Bundle

import androidx.preference.PreferenceFragmentCompat
import com.egor.gmk.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}