package com.mlab.gemma4.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mlab.gemma4.chat.ChatMessage
import com.mlab.gemma4.chat.ChatSpacing
import com.mlab.gemma4.ui.theme.Gemma4Theme
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ChatMessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (message.isUser) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    val textColor = if (message.isUser) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val accessibilityLabel = if (message.isUser) {
        stringResource(R.string.chat_message_user, message.text)
    } else {
        stringResource(R.string.chat_message_assistant, message.text)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = alignment,
    ) {
        MarkdownText(
            markdown = message.text,
            modifier = Modifier
                .widthIn(max = ChatSpacing.bubbleMaxWidth)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(ChatSpacing.bubbleCornerRadius),
                )
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .semantics {
                    contentDescription = accessibilityLabel
                },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatMessageBubblePreview() {
    Gemma4Theme {
        ChatMessageBubble(
            message = ChatMessage(
                id = 1L,
                text = "What can you help me with?",
                isUser = true,
            ),
        )
    }
}