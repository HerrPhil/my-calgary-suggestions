package com.example.mycalgarysuggestions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycalgarysuggestions.ui.MyCalgaryApp
import com.example.mycalgarysuggestions.ui.theme.MyCalgarySuggestionsTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCalgarySuggestionsTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
                        .padding(
                            start = WindowInsets
                                .safeDrawing
                                .asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets
                                .safeDrawing
                                .asPaddingValues()
                                .calculateEndPadding(layoutDirection)
                        )
                ) {
                    // For the adaptive layout logic
                    val windowSize = calculateWindowSizeClass(this)
                    MyCalgaryApp(
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCalgaryAppCompactPreview() {
    MyCalgarySuggestionsTheme {
        Surface {
            MyCalgaryApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
    }
}
