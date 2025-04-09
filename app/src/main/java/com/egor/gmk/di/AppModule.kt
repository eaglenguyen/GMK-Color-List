package com.egor.gmk.di

import android.app.Application
import androidx.room.Room
import com.egor.gmk.data.ColorRepository
import com.egor.gmk.room.ColorsDao
import com.egor.gmk.room.ColorsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ColorsDatabase {
        return Room.databaseBuilder(
            app,
            ColorsDatabase::class.java,
            "keycap_database"
        ).build()
    }

    @Provides
    fun provideKeycapDao(db: ColorsDatabase): ColorsDao {
        return db.colorsDao
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ColorsDao): ColorRepository {
        return ColorRepository(dao)
    }
}