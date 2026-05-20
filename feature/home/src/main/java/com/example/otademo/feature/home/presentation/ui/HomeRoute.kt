package com.example.otademo.feature.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    val viewModel: HomeViewModel = koinViewModel()

    HomeScreen(
        modifier = modifier,
        onCheckUpdate = viewModel::checkUpdate,
    )
}
