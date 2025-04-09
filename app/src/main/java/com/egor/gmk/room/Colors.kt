package com.egor.gmk.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Colors(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageRes: Int,     // Assuming you're using drawable resource IDs
    val title: String,
    val price: String,
    // val category: String   // e.g., "red", "blue", "add-on"
): Parcelable
