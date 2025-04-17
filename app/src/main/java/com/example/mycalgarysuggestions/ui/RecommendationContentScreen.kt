package com.example.mycalgarysuggestions.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.model.ContentUiState
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme

@Composable
fun RecommendationContentScreen(
    contentUiState: ContentUiState,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    contentPadding: PaddingValues,
    showNavigationButtons: Boolean = true,
    modifier: Modifier = Modifier
) {

    Log.i("RecommendationContentScreen", "TODO create recommendation content screen")

    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    val recommendationItem = contentUiState.recommendation


    // This works since the compact screen guarantees a recommendation item is selected
    // before the "Next" button can be clicked on the categories screen.
    // Therefore the "not selected" case is unreachable by the compact screen.
    // The expanded screen can have no recommendation and does not need navigation buttons
    // when the recommendation item is "not selected".
    if (recommendationItem == null) { // for expanded screen "not selected" case

        // nested in the column
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding())
                .background(color = MaterialTheme.colorScheme.tertiary)
        ) {

            Text(
                text = stringResource(R.string.no_recommendation),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onTertiary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        }

    } else { // for expanded and compact screen "selected" case

        Box(
            modifier = modifier
                .verticalScroll(state = scrollState)
                .padding(top = contentPadding.calculateTopPadding())
        ) { // Box with scrolling start

            Column(
                modifier = Modifier
                    .padding(
                        bottom = contentPadding.calculateTopPadding(),
                        start = contentPadding.calculateStartPadding(layoutDirection),
                        end = contentPadding.calculateEndPadding(layoutDirection)
                    )
            ) {


                // nested in the column
                Box(
                    modifier = Modifier.background(MaterialTheme.colorScheme.scrim)
                ) {

                    if (recommendationItem.logoResource != null) {

                        Image(
                            painter = painterResource(recommendationItem.logoResource),
                            contentDescription = null,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxWidth()
                        )

                    }

                }

                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {

                    Box(
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_small))
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Address: ${stringResource(recommendationItem.addressResource)}",
                            style = MaterialTheme.typography.bodySmall,
                        )

                    }

                    Spacer(Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = dimensionResource(R.dimen.padding_small))
                    ) {
                        Text(
                            text = "Rating: ${stringResource(recommendationItem.ratingResource)}",
                            style = MaterialTheme.typography.labelMedium,
                        )

                    }

                }

                Text(
                    text = stringResource(recommendationItem.descriptionResource),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                        horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                    )
                )



                if (showNavigationButtons) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onCancelButtonClicked
                        ) {
                            Text(stringResource(R.string.cancel).uppercase())
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = onNextButtonClicked
                        ) {
                            Text(stringResource(R.string.submit).uppercase())
                        }
                    }

                }

            } // end Column

        } // Box with scrolling end

    } // refactor

}

@Preview(showBackground = true)
@Composable
fun RecommendationContentScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            RecommendationContentScreen(
                contentUiState = ContentUiState(
                    recommendation = ContentItem.RecommendationItem(
                        nameResource = R.string.analog_name,
                        ratingResource = R.string.analog_rating,
                        descriptionResource = R.string.analog_description,
                        addressResource = R.string.analog_address,
                        logoResource = R.drawable.analog_coffee_logo
                    )
                ),
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}