package com.example.mycalgarysuggestions.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentUiState
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme

@Composable
fun RecommendationContentScreen(
    contentUiState: ContentUiState,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Log.i("RecommendationContentScreen", "TODO create recommendation content screen")

    Box(modifier = Modifier) {
        Text(text = "TODO create recommendation content screen")
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
}

@Preview(showBackground = true)
@Composable
fun RecommendationContentScreenPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            RecommendationContentScreen(
                contentUiState = ContentUiState(),
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}