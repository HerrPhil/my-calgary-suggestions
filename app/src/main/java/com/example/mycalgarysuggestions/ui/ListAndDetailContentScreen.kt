package com.example.mycalgarysuggestions.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.datasource.DataSource
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.model.ContentUiState
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme

@Composable
fun ListAndDetailContentScreen(
    options: List<ContentItem>,
    contentUiState: ContentUiState,
    contentPadding: PaddingValues,
    onSelectionChanged: (ContentItem) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    BackHandler {
        onBackPressed()
    }

    Box(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // Confident that CategoryItem extends ContentItem
            BaseContentScreen(
                options = options,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                onSelectionChanged = onSelectionChanged,
                showNavigationButtons = false,
                modifier = modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .padding(
                        top = contentPadding.calculateTopPadding(),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
                    .verticalScroll(rememberScrollState())
            )

            RecommendationContentScreen(
                contentUiState = contentUiState,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                contentPadding = contentPadding,
                showNavigationButtons = false,
                modifier = Modifier.weight(3f)
            )

        }

    }

}

@Preview(
    name = "Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=800dp,height=600dp",
    showBackground = true
)
@Composable
fun ListAndDetailContentCategoryListStateScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            ListAndDetailContentScreen(
                options = DataSource.categoryItems,
                contentUiState = ContentUiState(),
                contentPadding = PaddingValues(0.dp),
                onSelectionChanged = {},
                onBackPressed = {},
                modifier = Modifier
            )
        }
    }
}

@Preview(
    name = "Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=800dp,height=600dp",
    showBackground = true
)
@Composable
fun ListAndDetailContentRecommendationListStateScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            ListAndDetailContentScreen(
                options = DataSource.getRecommendations(DataSource.categoryItems[0].categoryType),
                contentUiState = ContentUiState(),
                contentPadding = PaddingValues(0.dp),
                onSelectionChanged = {},
                onBackPressed = {},
                modifier = Modifier
            )
        }
    }
}

@Preview(
    name = "Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=800dp,height=600dp",
    showBackground = true
)
@Composable
fun ListAndDetailContentRecommendationItemSelectedStateScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            ListAndDetailContentScreen(
                options = DataSource.getRecommendations(DataSource.categoryItems[3].categoryType),
                contentUiState = ContentUiState(
                    recommendation = ContentItem.RecommendationItem(
                        nameResource = R.string.bagolac_name,
                        ratingResource = R.string.bagolac_rating,
                        descriptionResource = R.string.bagolac_description,
                        addressResource = R.string.bagolac_address,
                        logoResource = R.drawable.bagolac_logo
                    )
                ),
                contentPadding = PaddingValues(0.dp),
                onSelectionChanged = {},
                onBackPressed = {},
                modifier = Modifier
            )
        }
    }
}
