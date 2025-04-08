package com.egor.gmk.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ColorsDao {
    @Query("SELECT * FROM colors WHERE category = :category")
    fun getKeycapsByCategory(category: String): LiveData<List<Colors>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keycaps: List<Colors>)
}