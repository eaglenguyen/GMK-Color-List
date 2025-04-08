package com.egor.gmk.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Colors::class], version = 1)

abstract class ColorsDatabase: RoomDatabase() {
    abstract val colorsDao: ColorsDao
}