package com.example.mycalgarysuggestions.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.model.ContentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContentUiState())
    val uiState: StateFlow<ContentUiState> = _uiState.asStateFlow()

    fun updateCategory(category: ContentItem.CategoryItem) {
        updateItem(category)
    }

    fun updateRecommendation(recommendation: ContentItem.RecommendationItem) {
        updateItem(recommendation)
    }

    fun resetContent() {
        _uiState.value = ContentUiState()
    }

    fun getCategoryType(): ContentItem.CategoryType {
        return _uiState.value.category?.categoryType ?: ContentItem.CategoryType.NoCategory
    }

    @StringRes
    fun getCategoryTitle(): Int {
        return _uiState.value.category?.categoryType?.title ?: R.string.default_category
    }

    @StringRes
    fun getRecommendationName(): Int {
        return _uiState.value.recommendation?.nameResource ?: R.string.default_recommendation
    }

    private fun updateItem(newItem: ContentItem) {
        _uiState.update { currentState ->
            currentState.copy(
                category = if (newItem is ContentItem.CategoryItem) newItem else currentState.category,
                recommendation = if (newItem is ContentItem.RecommendationItem) newItem else currentState.recommendation
            )
        }
    }
}
