package com.egor.gmk.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ColorsDao {


    @Query("SELECT * FROM colors ORDER BY title ASC")
    fun getAllKeycaps(): Flow<List<Colors>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(keycaps: List<Colors>)

    @Query(
        """
            SELECT *
            FROM colors
            WHERE LOWER (title) LIKE '%' || LOWER (:query) || '%'
        """
    )
    suspend fun searchKeycapName(query: String): List<Colors>


    @Query("DELETE FROM colors")
    suspend fun deleteAllColors()

    // test
}