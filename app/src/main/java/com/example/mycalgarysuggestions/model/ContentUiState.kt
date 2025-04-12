package com.example.mycalgarysuggestions.model

data class ContentUiState(
    val category: ContentItem.CategoryItem? = null,
    val recommendation: ContentItem.RecommendationItem? = null
)
