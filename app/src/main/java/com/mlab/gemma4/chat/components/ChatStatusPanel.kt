package com.mlab.gemma4.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mlab.gemma4.R
import com.mlab.gemma4.chat.ChatSpacing
import com.mlab.gemma4.chat.ChatUiState
import com.mlab.gemma4.ui.theme.Gemma4Theme

@Composable
fun ChatLoadingPanel(
    state: ChatUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(ChatSpacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularWavyProgressIndicator()
        Text(
            text = when (state) {
                is ChatUiState.PreparingModel -> state.statusMessage
                is ChatUiState.LoadingModel -> state.statusMessage
                else -> stringResource(R.string.chat_initializing)
            },
            modifier = Modifier.padding(top = ChatSpacing.md),
            style = MaterialTheme.typography.bodyLarge,
        )
        if (state is ChatUiState.PreparingModel && state.progress > 0f) {
            val progressLabel = stringResource(
                R.string.chat_copy_progress,
                (state.progress * 100).toInt(),
            )
            LinearWavyProgressIndicator(
                progress = { state.progress },
                amplitude = { 0.8f },
                wavelength = 20.dp,
                waveSpeed = 40.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = ChatSpacing.md)
                    .semantics { contentDescription = progressLabel },
            )
        }
    }
}

@Composable
fun ChatErrorPanel(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(ChatSpacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = ChatSpacing.md),
        ) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatLoadingPanelPreview() {
    Gemma4Theme {
        ChatLoadingPanel(
            state = ChatUiState.PreparingModel(
                progress = 0.42f,
                statusMessage = "Copying model to device storage…",
            ),
        )
    }
}