package com.egor.gmk.ui.colorfrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egor.gmk.data.ColorRepository
import com.egor.gmk.room.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val repository: ColorRepository
):ViewModel() {


    // private bcos only viewmodel can update the variable
    private val _searchResults = MutableStateFlow<List<Colors>>(emptyList())

    // Exposes _searchResults as read only for fragments. can only be collected and not modified
    val searchResults: StateFlow<List<Colors>> = _searchResults

    // populates list once viewmodel is created
    init {
        viewModelScope.launch {
            repository.allKeycaps.collect { all ->
                _searchResults.value = all
            }
        }
    }

    // Insert keycaps from class (used for seeding DB)
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
            val results = if (query.isBlank()) {
                // returns full list like init block
                repository.allKeycaps.first()
            } else {
                // Executes SQL query and updates emptylist
                repository.searchKeycapName(query)
            }
            _searchResults.value = results
        }
    }




}