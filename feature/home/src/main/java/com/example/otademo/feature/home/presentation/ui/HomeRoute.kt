package com.example.otademo.feature.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.otademo.feature.updater.presentation.ui.UpdaterRoute

@Composable
fun HomeRoute(modifier: Modifier = Modifier) {
    UpdaterRoute { onCheckUpdate ->
        HomeScreen(
            modifier = modifier,
            onCheckUpdate = onCheckUpdate,
        )
    }
}
