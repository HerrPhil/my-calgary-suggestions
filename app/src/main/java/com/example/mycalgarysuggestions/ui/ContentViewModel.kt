package com.example.mycalgarysuggestions.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.model.ContentUiState
import com.example.mycalgarysuggestions.utils.ContentSelection
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

    fun updateSelectionToCategory() {
        _uiState.update { currentState ->
            currentState.copy(
                currentContentSelection = ContentSelection.CATEGORY_SELECTION
            )
        }
    }

    fun updateRecommendation(recommendation: ContentItem.RecommendationItem) {
        updateItem(recommendation)
    }

    fun updateSelectionToRecommendation() {
        _uiState.update { currentState ->
            currentState.copy(
                currentContentSelection = ContentSelection.RECOMMENDATION_SELECTION
            )
        }
    }

    fun getCategoryType(): ContentItem.CategoryType {
        return _uiState.value.category.categoryType
    }

    @StringRes
    fun getCategoryTitle(): Int {
        return _uiState.value.category.categoryType.title
    }

    @StringRes
    fun getRecommendationName(): Int {
        return _uiState.value.recommendation?.nameResource ?: R.string.default_recommendation
    }

    fun getCurrentContentSelection(): ContentSelection {
        return _uiState.value.currentContentSelection
    }

    // There is no navigateToCategoryList() method since the app effectively does this
    // when it resets the content on the event of navigating from recommendation to category list.

    fun navigateToRecommendationList() {
        _uiState.update {
            it.copy(isShowingRecommendationList = true)
        }
    }

    fun resetContent() {
        _uiState.value = ContentUiState()
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
