package com.example.mycalgarysuggestions.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycalgarysuggestions.R
import com.example.mycalgarysuggestions.datasource.DataSource
import com.example.mycalgarysuggestions.model.ContentItem
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme
import com.example.mycalgarysuggestions.utils.ContentSelection
import com.example.mycalgarysuggestions.utils.SuggestionContentType

enum class MyCalgaryScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Category(title = R.string.my_category_screen),
    Recommendation(title = R.string.my_recommendation_screen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCalgaryAppBar(
    @StringRes currentTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit, // What actually to do when the user navigates up
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentTitle)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCalgaryApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val contentType: SuggestionContentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> SuggestionContentType.LIST_ONLY
        WindowWidthSizeClass.Medium -> SuggestionContentType.LIST_ONLY
        WindowWidthSizeClass.Expanded -> SuggestionContentType.LIST_AND_DETAIL
        else -> SuggestionContentType.LIST_ONLY
    }

    if (contentType == SuggestionContentType.LIST_ONLY) {
        MyCalgaryCompactScreen()
    } else {
        MyCalgaryExpandedScreen()
    }
}

@Composable
fun MyCalgaryCompactScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    // get the current back stack entry of nav host controller - where is the app
    val backStackEntry by navController.currentBackStackEntryAsState() // state flow for composition

    // get the current screen
    val currentScreen =
        MyCalgaryScreen.valueOf(backStackEntry?.destination?.route ?: MyCalgaryScreen.Start.name)

    val viewModel: ContentViewModel = viewModel()

    @StringRes val titleResourceId = when (currentScreen) {
        MyCalgaryScreen.Start -> MyCalgaryScreen.Start.title
        MyCalgaryScreen.Category -> viewModel.getCategoryTitle()
        MyCalgaryScreen.Recommendation -> viewModel.getRecommendationName()
    }

    Log.i("MyCalgaryApp", "selected title ${stringResource(titleResourceId)}")

    Scaffold(
        topBar = {
            MyCalgaryAppBar(
                currentTitle = titleResourceId,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        // Add all the destinations (screens/routes) to NavHost

        NavHost(
            navController = navController,
            startDestination = MyCalgaryScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // The start screen
            composable(route = MyCalgaryScreen.Start.name) {

                Log.i("MyCalgaryScreen", "inside nav graph content of start screen.")

                StartContentScreen(
                    options = DataSource.categoryItems,
                    onCancelButtonClicked = {
                        Log.i("StartContentScreen", "event of cancel button clicked")
                    },
                    onNextButtonClicked = { navController.navigate(MyCalgaryScreen.Category.name) },
                    onSelectionChanged = { viewModel.updateCategory(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                )
            }

            // The category screen
            composable(route = MyCalgaryScreen.Category.name) {

                Log.i("MyCalgaryScreen", "inside nav graph content of category screen.")

                CategoryContentScreen(
                    options = DataSource.getRecommendations(viewModel.getCategoryType()),
                    onCancelButtonClicked = {
                        Log.i("CategoryContentScreen", "event of cancel button clicked")
                        navigateToStart(navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(MyCalgaryScreen.Recommendation.name)
                    },
                    onSelectionChanged = { viewModel.updateRecommendation(it) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                )
            }

            // The recommendation screen
            composable(route = MyCalgaryScreen.Recommendation.name) {

                Log.i("MyCalgaryScreen", "inside nav graph content of recommendation screen.")

                RecommendationContentScreen(
                    contentUiState = uiState,
                    onCancelButtonClicked = {
                        Log.i("RecommendationContentScreen", "event of cancel button clicked")
                        navigateToStart(navController)
                    },
                    onNextButtonClicked = {
                        Log.i("RecommendationContentScreen", "event of cancel button clicked")
                        navigateToStart(navController)
                    },
                    innerPadding
                )
            }

        }

    }

}

// I am experimenting with the non-NavHostController state tracking for the expanded screen,
// which has one Composable screen and receives different list data based on state of the
// loaded list. We will see how this goes. Otherwise, I can define another nav-graph for
// two expanded screens, one for category list and one for recommendation list.
@SuppressLint("ContextCastToActivity")
@Composable
fun MyCalgaryExpandedScreen() {
    Log.i(
        "MyCalgaryExpandedScreen",
        "TODO implement the adaptive layout with non-navHostController UI state, one screen"
    )

    // initialize the view model, as per usual.
    // this also initializes the state, with its initial, default category,
    // which is the first category in the category list (aka "Pubs and Cafes").
    val viewModel: ContentViewModel = viewModel()

    // fetch the UI state that triggers (re)composition.
    val uiState by viewModel.uiState.collectAsState()

    // get the current content selection
    val expandedScreenCurrentContentSelection = viewModel.getCurrentContentSelection()

    // I am predicting that the title logic will work the same. The list and detail screen
    // delegates to the same base content screen for the list that only marks radio as "selected"
    // after they are clicked, and not based on initial UI state of current list type.
    @StringRes val titleResourceId = when (expandedScreenCurrentContentSelection) {
        ContentSelection.NO_SELECTION -> MyCalgaryScreen.Start.title
        ContentSelection.CATEGORY_SELECTION -> viewModel.getCategoryTitle()
        ContentSelection.RECOMMENDATION_SELECTION -> viewModel.getRecommendationName()
    }

    val isUiShowingRecommendationList = uiState.isShowingRecommendationList

    // By resetting content, on the navigate up action,
    // the recommendation is cleared, the navigate to category list UI state flag is set,
    // and current content selection is NO_SELECTION
    Scaffold(
        topBar = {
            MyCalgaryAppBar(
                currentTitle = titleResourceId,
                canNavigateBack = isUiShowingRecommendationList,
                navigateUp = { viewModel.resetContent() }
            )
        }
    ) { innerPadding ->

        // Decide what type of options to pass to the list and detail screen
        val currentOptions = when (isUiShowingRecommendationList) {
            true -> DataSource.getRecommendations(viewModel.getCategoryType())
            false -> DataSource.categoryItems
        }

        // Decide what function data type that onSelectionChanged will be assigned
        val selectionFunction: (ContentItem) -> Unit = when (isUiShowingRecommendationList) {
            true -> { item: ContentItem ->
                if (item is ContentItem.RecommendationItem) viewModel.updateRecommendation(item)
                viewModel.updateSelectionToRecommendation()
            }

            false -> { item: ContentItem ->
                if (item is ContentItem.CategoryItem) viewModel.updateCategory(item)
                viewModel.updateSelectionToCategory()
                viewModel.navigateToRecommendationList()
            }
        }

        // Decide what function to support on Android system backpress
        val activity = LocalContext.current as Activity

        // By resetting, the recommendation is cleared, the navigate to category list UI state
        // flag is set, and current content selection is NO_SELECTION
        val onBackPressedFromShowingRecommendationList = {
            viewModel.resetContent()
        }

        val onBackPressedFromShowingCategoryList = {
            activity.finish()
        }

        val onBackPressedOfList = when (isUiShowingRecommendationList) {
            true -> onBackPressedFromShowingRecommendationList
            false -> onBackPressedFromShowingCategoryList
        }

        ListAndDetailContentScreen(
            options = currentOptions,
            contentUiState = uiState,
            contentPadding = innerPadding,
            onSelectionChanged = selectionFunction,
            onBackPressed = onBackPressedOfList
        )
    }

}

private fun navigateToStart(navController: NavHostController) {
    navController.popBackStack(MyCalgaryScreen.Start.name, inclusive = false)
}

@Composable
fun MyTypographyCheck() {

    // look at all my typography options

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        Text(
            text = "display large is roboto mono bold size 57sp line height 64sp line spacing -0.2sp",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "display medium is roboto mono semi-bold size 45sp line height 52sp line spacing 0sp",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "display small is roboto mono regular size 36sp line height 44sp line spacing 0sp",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "headline large is roboto mono bold size 32sp line height 40sp line spacing 0sp",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "headline medium is roboto mono semi-bold size 28sp line height 46sp line spacing 0sp",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "headline small is roboto mono regular size 24sp line height 32sp line spacing 0sp",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "title large is roboto mono bold size 22sp line height 28sp line spacing 0sp",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "title medium is roboto mono semi-bold size 16sp line height 24sp line spacing 0.2sp",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "title small is roboto mono regular size 14sp line height 20sp line spacing 0.1sp",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
    }


}

@Preview
@Composable
fun MyTopographyPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            MyTypographyCheck()
        }
    }
}
