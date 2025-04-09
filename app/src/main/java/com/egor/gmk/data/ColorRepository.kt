package com.egor.gmk.data

import com.egor.gmk.room.Colors
import com.egor.gmk.room.ColorsDao
import kotlinx.coroutines.flow.Flow

class ColorRepository(private val dao: ColorsDao) {

    val allKeycaps: Flow<List<Colors>> = dao.getAllKeycaps()

    suspend fun insertAll(keycaps: List<Colors>) {
        dao.insertAll(keycaps)
    }




}