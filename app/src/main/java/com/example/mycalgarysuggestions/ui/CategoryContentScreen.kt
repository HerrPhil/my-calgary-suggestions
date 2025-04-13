package com.example.mycalgarysuggestions.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.datasource.DataSource
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme

@Composable
fun CategoryContentScreen(
    options: List<ContentItem.RecommendationItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (ContentItem.RecommendationItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Log.i("CategoryContentScreen", "TODO delegate to base content screen")

    // Confident that RecommendationItem extends ContentItem
    @Suppress("UNCHECKED_CAST")
    BaseContentScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (ContentItem) -> Unit,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryContentScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            CategoryContentScreen(
                options = DataSource.getRecommendations(ContentItem.CategoryType.PhoRestaurants),
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = {},
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}