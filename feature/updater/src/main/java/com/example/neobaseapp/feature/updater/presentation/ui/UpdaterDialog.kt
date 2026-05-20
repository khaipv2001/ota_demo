package com.example.neobaseapp.feature.updater.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.neobaseapp.feature.updater.R
import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo
import com.example.neobaseapp.feature.updater.presentation.state.UpdaterUiState

@Composable
fun UpdaterDialog(
    uiState: UpdaterUiState,
    onDismiss: () -> Unit,
    onUpdateNow: () -> Unit,
) {
    if (!uiState.isDialogVisible || uiState.updateInfo == null) return

    val description =
        when (val downloadState = uiState.downloadState) {
            DownloadState.Idle -> {
                uiState.updateInfo.releaseNotes
            }

            is DownloadState.Downloading -> {
                stringResource(
                    R.string.downloading,
                    downloadState.progress,
                )
            }

            is DownloadState.Success -> {
                stringResource(R.string.installing)
            }

            is DownloadState.Error -> {
                downloadState.throwable.message
                    ?: uiState.updateInfo.releaseNotes
            }
        }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.update_available_title)) },
        text = {
            if (uiState.downloadState is DownloadState.Downloading) {
                LinearProgressIndicator(progress = { (uiState.downloadState as DownloadState.Downloading).progress / 100f })
            }
            Text(text = description)
        },
        confirmButton = {
            TextButton(
                onClick = onUpdateNow,
                enabled = uiState.downloadState == DownloadState.Idle,
            ) {
                Text(text = stringResource(R.string.update_now))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.later))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun UpdaterDialogIdlePreview() {
    UpdaterDialog(
        uiState =
            UpdaterUiState(
                isDialogVisible = true,
                updateInfo =
                    UpdateInfo(
                        versionCode = 2,
                        versionName = "1.1.0",
                        releaseNotes = "Improve kiosk stability and add update readiness checks.",
                        downloadUrl = "https://example.com/releases/ota-demo-1.1.0.apk",
                        isForceUpdate = false,
                    ),
            ),
        onDismiss = {},
        onUpdateNow = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun UpdaterDialogDownloadingPreview() {
    UpdaterDialog(
        uiState =
            UpdaterUiState(
                isDialogVisible = true,
                updateInfo =
                    UpdateInfo(
                        versionCode = 2,
                        versionName = "1.1.0",
                        releaseNotes = "Improve kiosk stability and add update readiness checks.",
                        downloadUrl = "https://example.com/releases/ota-demo-1.1.0.apk",
                        isForceUpdate = false,
                    ),
                downloadState = DownloadState.Downloading(progress = 42),
            ),
        onDismiss = {},
        onUpdateNow = {},
    )
}
