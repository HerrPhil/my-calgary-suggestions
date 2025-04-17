package com.example.mycalgarysuggestions.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.model.ContentItem

@Composable
fun BaseContentScreen(
    options: List<ContentItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (ContentItem) -> Unit,
    showNavigationButtons: Boolean = true,
    modifier: Modifier = Modifier
) {

    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { item ->

            val currentItemName = stringResource(getNameResource(item))

            val onClick = {
                selectedItemName = currentItemName
                onSelectionChanged(item)
            }

            MenuItemRow(
                item = item,
                selectedItemName = selectedItemName,
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
                    .selectable(
                        selected = selectedItemName == currentItemName,
                        onClick = onClick
                    )
            )
        }

        if (showNavigationButtons) {
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
    }
}

@Composable
fun MenuItemRow(
    item: ContentItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius))
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val currentItemIcon = getIconResource(item)

            val currentItemName = stringResource(getNameResource(item))

            Image(
                painter = painterResource(currentItemIcon),
                contentDescription = currentItemName,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )

            RadioButton(
                selected = selectedItemName == currentItemName,
                onClick = onClick
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = currentItemName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
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

@StringRes
private fun getNameResource(item: ContentItem): Int {
    var nameResourceId = 0

    if (item is ContentItem.CategoryItem) {
        nameResourceId = item.categoryType.title
    }
    if (item is ContentItem.RecommendationItem) {
        nameResourceId = item.nameResource
    }

    return nameResourceId
}

@DrawableRes
private fun getIconResource(item: ContentItem): Int {
    var iconResourceId = 0

    if (item is ContentItem.CategoryItem) {
        iconResourceId = item.categoryType.icon
    }
    if (item is ContentItem.RecommendationItem) {
        iconResourceId = item.iconResource
    }

    return iconResourceId
}

