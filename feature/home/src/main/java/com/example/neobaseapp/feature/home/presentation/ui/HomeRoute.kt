package com.example.neobaseapp.feature.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.neobaseapp.feature.updater.presentation.ui.UpdaterRoute

@Composable
fun HomeRoute(modifier: Modifier = Modifier) {
    UpdaterRoute { onCheckUpdate ->
        HomeScreen(
            modifier = modifier,
            onCheckUpdate = onCheckUpdate,
        )
    }
}
