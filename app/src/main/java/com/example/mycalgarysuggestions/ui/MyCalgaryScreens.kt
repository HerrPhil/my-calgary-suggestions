package com.example.mycalgarysuggestions.ui

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
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
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

    // TODO adaptive layout based on window size
    // TODO adaptive layout needs default category on start screen on first visit, to show recommendations.

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
                        viewModel.resetContent()
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
                        viewModel.resetContent()
                        navigateToStart(navController)
                    },
                    onNextButtonClicked = {


                        // TODO for testing purposes
                        Log.i("ContentViewModel", "get any recommendation to test navigation")
                        viewModel.updateRecommendation(
                            DataSource.getRecommendations(ContentItem.CategoryType.PubsAndCafes)[0]
                        )




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
                        viewModel.resetContent()
                        navigateToStart(navController)
                    },
                    onNextButtonClicked = {
                        viewModel.resetContent()
                        navigateToStart(navController)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                )
            }

        }

    }

}

private fun navigateToStart(navController: NavHostController) {
    navController.popBackStack(MyCalgaryScreen.Start.name, inclusive = false)
}

@Composable
fun MyTypographyCheck() {
    // look at all my typography options

//    LazyColumn(
//        modifier = Modifier,
//        verticalArrangement = Arrangement.spacedBy(2.dp)
//    ) {
//
//        item {
//            Text(
//                text = "display large is roboto mono bold size 57sp line height 64sp line spacing -0.2sp",
//                style = MaterialTheme.typography.displayLarge
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        item {
//            Text(
//                text = "display medium is roboto mono bold size 45sp line height 52sp line spacing 0sp",
//                style = MaterialTheme.typography.displayMedium
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//        item {
//            Text(
//                text = "display small is roboto mono bold size 36sp line height 44sp line spacing 0sp",
//                style = MaterialTheme.typography.displayMedium
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//        item {
//            Text(
//                text = "headline large is roboto mono bold size 32sp line height 40sp line spacing 0sp",
//                style = MaterialTheme.typography.headlineLarge
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }


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
