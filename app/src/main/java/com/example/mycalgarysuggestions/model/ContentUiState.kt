package com.example.mycalgarysuggestions.model

import com.example.mycalgarysuggestions.datasource.DataSource
import com.example.mycalgarysuggestions.utils.ContentSelection

data class ContentUiState(
    val category: ContentItem.CategoryItem = DataSource.defaultCategory,
    val recommendation: ContentItem.RecommendationItem? = null,
    val currentContentSelection: ContentSelection = ContentSelection.NO_SELECTION,
    val isShowingRecommendationList: Boolean = false
)
