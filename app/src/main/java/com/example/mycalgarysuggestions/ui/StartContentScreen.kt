package com.example.mycalgarysuggestions.ui

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
fun StartContentScreen(
    options: List<ContentItem.CategoryItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (ContentItem.CategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {

    // Confident that CategoryItem extends ContentItem
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
fun StartContentScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            StartContentScreen(
                options = DataSource.categoryItems,
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