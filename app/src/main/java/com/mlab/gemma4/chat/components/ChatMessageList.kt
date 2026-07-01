package com.mlab.gemma4.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.mlab.gemma4.R
import com.mlab.gemma4.chat.ChatListItem
import com.mlab.gemma4.chat.ChatMessage
import com.mlab.gemma4.chat.ChatSpacing
import com.mlab.gemma4.chat.ChatUiState

@Composable
fun ChatMessageList(
    state: ChatUiState.Ready,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val listItems by remember(state.messages, state.isGenerating, state.streamingText) {
        derivedStateOf {
            buildList {
                state.messages.forEach { message ->
                    add(ChatListItem.Message(message))
                }
                if (state.isGenerating) {
                    if (state.streamingText.isNotEmpty()) {
                        add(ChatListItem.Streaming(state.streamingText))
                    } else {
                        add(ChatListItem.Thinking)
                    }
                }
            }
        }
    }

    val scrollBucket by remember(state.streamingText) {
        derivedStateOf { state.streamingText.length / 80 }
    }

    LaunchedEffect(listItems.size, scrollBucket) {
        val lastIndex = listItems.lastIndex
        if (lastIndex >= 0) {
            if (state.isGenerating && state.streamingText.isNotEmpty()) {
                listState.scrollToItem(lastIndex)
            } else {
                listState.animateScrollToItem(lastIndex)
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(ChatSpacing.sm),
    ) {
        item { Spacer(Modifier.height(ChatSpacing.sm)) }
        items(
            items = listItems,
            key = { it.key },
            contentType = { it.contentType },
        ) { item ->
            when (item) {
                is ChatListItem.Message -> ChatMessageBubble(message = item.message)
                is ChatListItem.Streaming -> ChatMessageBubble(
                    message = ChatMessage(
                        id = -1L,
                        text = item.text,
                        isUser = false,
                    ),
                )
                ChatListItem.Thinking -> ChatThinkingIndicator()
            }
        }
        item { Spacer(Modifier.height(ChatSpacing.sm)) }
    }
}

@Composable
private fun ChatThinkingIndicator(
    modifier: Modifier = Modifier,
) {
    val label = stringResource(R.string.chat_generating)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = label },
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(ChatSpacing.sm),
        )
    }
}