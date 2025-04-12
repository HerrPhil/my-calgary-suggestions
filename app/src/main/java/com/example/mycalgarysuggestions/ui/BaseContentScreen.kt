package com.example.mycalgarysuggestions.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem

@Composable
fun BaseContentScreen(
    options: List<ContentItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (ContentItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Log.i("BaseContentScreen", "TODO create base content screen")

    // TODO For navigation test, cannot be empty to enable "Next" button
    var selectedItemName by rememberSaveable { mutableStateOf("TEST VALUE") }
//    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier) {
        Text(text = "TODO create base content screen")
    }

    MenuScreenButtonGroup(
        selectedItemName = selectedItemName,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = {
            // Assert not null bc next button is not enabled unless selectedItem is not null.
            onNextButtonClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
    )

}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
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
            // the button is enabled when the user makes a selection
            enabled = selectedItemName.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}

