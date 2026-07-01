package com.mlab.gemma4.chat

import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mlab.gemma4.R
import com.mlab.gemma4.chat.components.ChatErrorPanel
import com.mlab.gemma4.chat.components.ChatInputBar
import com.mlab.gemma4.chat.components.ChatLoadingPanel
import com.mlab.gemma4.chat.components.ChatMessageList
import com.mlab.gemma4.ui.theme.Gemma4Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            ChatTopBar(
                isGenerating = (uiState as? ChatUiState.Ready)?.isGenerating ?: false,
                isOnGpu = uiState.isOnGpu,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surfaceContainerLow,
                            MaterialTheme.colorScheme.background
                        ),
                    ),
                ),
        ) {
            ChatScreenContent(
                uiState = uiState,
                onInputChange = viewModel::updateInput,
                onSend = viewModel::sendMessage,
                onRetry = viewModel::initialize,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatTopBar(
    isGenerating: Boolean = true,
    isOnGpu: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
) {
    Surface(
        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
    ) {
        TopAppBar(
            modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.chat_title),
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        StatusLine(isGenerating = isGenerating)
                    }
                    ElevatedSuggestionChip(
                        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                            containerColor = if (isOnGpu)
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {},
                        label = {
                            Text(
                                text = if (isOnGpu) "On GPU" else "On CPU",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                        }
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                scrolledContainerColor = Color.Unspecified,
                navigationIconContentColor = Color.Unspecified,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = Color.Unspecified
            ),
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
private fun StatusLine(isGenerating: Boolean) {
    val label = if (isGenerating) "Generating…" else "Ready"
    val dotColor = if (isGenerating) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.primary
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(dotColor),
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ChatScreenContent(
    uiState: ChatUiState,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Key on the state's class rather than the state itself, so a crossfade
    // only plays on real phase changes (Idle -> Loading -> Ready), not on
    // every keystroke or token streamed into a Ready state's message list.
    AnimatedContent(
        targetState = uiState,
        contentKey = { it::class },
        transitionSpec = {
            (fadeIn(animationSpec = tween(220)))
                .togetherWith(fadeOut(animationSpec = tween(140)))
        },
        modifier = modifier,
        label = "chat_state_transition",
    ) { state ->
        when (state) {
            is ChatUiState.Idle,
            is ChatUiState.PreparingModel,
            is ChatUiState.LoadingModel,
                -> ChatLoadingPanel(
                state = state,
                modifier = Modifier.fillMaxSize(),
            )

            is ChatUiState.Error -> ChatErrorPanel(
                message = state.message,
                onRetry = onRetry,
                modifier = Modifier.fillMaxSize(),
            )

            is ChatUiState.Ready -> ChatReadyPanel(
                state = state,
                onInputChange = onInputChange,
                onSend = onSend,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun ChatReadyPanel(
    state: ChatUiState.Ready,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val canSend by remember(state.inputText, state.isGenerating) {
        derivedStateOf {
            state.inputText.isNotBlank() && !state.isGenerating
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        ChatMessageList(
            state = state,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = ChatSpacing.md)
        )

        // Rounded top corners + elevated container color turn this into a
        // floating composer; ChatInputBar owns the Surface itself, so there's
        // no second wrapper here competing for the same corners.
        ChatInputBar(
            inputText = state.inputText,
            onInputChange = onInputChange,
            onSend = onSend,
            enabled = !state.isGenerating,
            canSend = canSend,
            modifier = Modifier
                .imePadding()
                .navigationBarsPadding(),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 6.dp,
            shadowElevation = 8.dp,
        )
    }
}

@Preview(showBackground = true, name = "Ready - Light")
@Composable
private fun ChatScreenPreviewLight() {
    Gemma4Theme {
        ChatScreenContent(
            uiState = ChatUiState.Ready(
                messages = listOf(
                    ChatMessage(id = 1L, text = "Hello!", isUser = true),
                    ChatMessage(id = 2L, text = "Hi, how can I help?", isUser = false),
                ),
                inputText = "Tell me a joke",
            ),
            onInputChange = {},
            onSend = {},
            onRetry = {},
        )
    }
}

@Preview(showBackground = true, name = "Generating")
@Composable
private fun ChatScreenPreviewGenerating() {
    Gemma4Theme {
        ChatScreenContent(
            uiState = ChatUiState.Ready(
                messages = listOf(
                    ChatMessage(id = 1L, text = "Write a haiku about rivers", isUser = true),
                ),
                inputText = "",
                isGenerating = true,
            ),
            onInputChange = {},
            onSend = {},
            onRetry = {},
        )
    }
}