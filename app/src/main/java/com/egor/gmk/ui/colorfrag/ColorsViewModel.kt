package com.egor.gmk.ui.colorfrag

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egor.gmk.data.ColorRepository
import com.egor.gmk.room.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val repository: ColorRepository
):ViewModel() {



    val allKeycaps: Flow<List<Colors>> = repository.allKeycaps

    private val _searchResults = MutableStateFlow<List<Colors>>(emptyList())
    val searchResults: StateFlow<List<Colors>> = _searchResults

    init {
        viewModelScope.launch {
            repository.allKeycaps.collect { all ->
                _searchResults.value = all
            }
        }
    }

    // Insert keycaps (used for seeding DB)
    fun insertKeycaps(keycaps: List<Colors>) {
        viewModelScope.launch {
            // Obtain a snapshot of current keycaps
            val existingColors = repository.allKeycaps.first()

            // Check if no existing keycaps has the same title
            val newKeycaps = keycaps.filter { newColor ->
                existingColors.none {it.title == newColor.title}
            }

            if(newKeycaps.isNotEmpty()) {
                repository.insertAll(newKeycaps)
            }

        }
    }

    fun searchKeycaps(query: String) {
        viewModelScope.launch {
            Log.d("Search", "Searching for: $query")

            val results = if (query.isBlank()) {
                repository.allKeycaps.first()
            } else {
                repository.searchKeycapName(query)
            }
            _searchResults.value = results
        }
    }




}