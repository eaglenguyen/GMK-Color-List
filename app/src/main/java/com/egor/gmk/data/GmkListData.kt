package com.egor.gmk.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GmkListData (
    var image: Int,
    var title: String,
    var price: String
): Parcelable