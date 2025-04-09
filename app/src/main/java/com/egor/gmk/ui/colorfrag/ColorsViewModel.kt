package com.egor.gmk.ui.colorfrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egor.gmk.data.ColorRepository
import com.egor.gmk.room.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val repository: ColorRepository
):ViewModel() {


    val allKeycaps: Flow<List<Colors>> = repository.allKeycaps


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


}