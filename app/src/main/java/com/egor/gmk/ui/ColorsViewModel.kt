package com.egor.gmk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egor.gmk.data.ColorRepository
import com.egor.gmk.room.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val repository: ColorRepository
):ViewModel() {


    fun getKeycapsByCategory(category: String): LiveData<List<Colors>> {
        return repository.getKeycapsByCategory(category)
    }

    // Insert keycaps (used for seeding DB)
    fun insertKeycaps(keycaps: List<Colors>) {
        viewModelScope.launch {
            repository.insertAll(keycaps)
        }
    }


}