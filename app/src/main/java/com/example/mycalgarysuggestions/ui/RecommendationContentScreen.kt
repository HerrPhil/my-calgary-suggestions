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
    modifier: Modifier = Modifier
) {

    Log.i("RecommendationContentScreen", "TODO create recommendation content screen")

    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    val recommendationItem = contentUiState.recommendation

    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {

        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {

            if (recommendationItem == null) {

                // nested in the column
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.onErrorContainer)
                ) {

                    Text(
                        text = stringResource(R.string.no_recommendation),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center
                    )

                }

            } else {

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

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                    Text(stringResource(R.string.cancel).uppercase())
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onNextButtonClicked
                ) {
                    Text(stringResource(R.string.submit).uppercase())
                }
            }

        } // end Column

    }

}

@Preview(showBackground = true)
@Composable
fun RecommendationContentScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            RecommendationContentScreen(
                contentUiState = ContentUiState(
                    recommendation = ContentItem.RecommendationItem(
                        nameResource = R.string.bagolac_name,
                        ratingResource = R.string.bagolac_rating,
                        descriptionResource = R.string.bagolac_description,
                        addressResource = R.string.bagolac_address,
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