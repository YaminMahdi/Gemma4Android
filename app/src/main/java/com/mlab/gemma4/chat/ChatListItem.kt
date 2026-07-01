package com.mlab.gemma4.chat

sealed class ChatListItem(
    val key: Any,
    val contentType: String,
) {
    data class Message(val message: ChatMessage) : ChatListItem(
        key = message.id,
        contentType = "message",
    )

    data class Streaming(val text: String) : ChatListItem(
        key = "streaming",
        contentType = "streaming",
    )

    data object Thinking : ChatListItem(
        key = "thinking",
        contentType = "thinking",
    )
}