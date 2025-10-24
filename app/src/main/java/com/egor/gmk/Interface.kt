package com.egor.gmk

import android.view.View
import com.egor.gmk.data.GmkListData

// interface to allow onclick logic

interface OnItemClickListener {
    fun onItemClick(position: Int, sharedImage: View, keycap: GmkListData)
}