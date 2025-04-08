package com.egor.gmk.data

import androidx.lifecycle.LiveData
import com.egor.gmk.room.Colors
import com.egor.gmk.room.ColorsDao

class ColorRepository(private val dao: ColorsDao) {
    fun getKeycapsByCategory(category: String): LiveData<List<Colors>> {
        return dao.getKeycapsByCategory(category)
    }

    suspend fun insertAll(keycaps: List<Colors>) {
        dao.insertAll(keycaps)
    }
}