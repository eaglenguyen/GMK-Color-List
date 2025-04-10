package com.egor.gmk.ui.colorfrag

sealed class SearchScreenUiEvent {
    data class onSearchQueryChange(val query: String): SearchScreenUiEvent()
}