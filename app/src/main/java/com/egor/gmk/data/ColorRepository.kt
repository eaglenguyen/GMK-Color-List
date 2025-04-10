package com.egor.gmk.data

import com.egor.gmk.room.Colors
import com.egor.gmk.room.ColorsDao
import com.egor.gmk.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ColorRepository(
    private val dao: ColorsDao
) {

    val allKeycaps: Flow<List<Colors>> = dao.getAllKeycaps()


    suspend fun insertAll(keycaps: List<Colors>) {
        dao.insertAll(keycaps)
    }

    suspend fun searchKeycapName(query: String): List<Colors> {
        return dao.searchKeycapName(query)
    }





}