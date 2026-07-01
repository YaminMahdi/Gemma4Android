package com.mlab.gemma4.chat

import androidx.compose.runtime.Immutable

@Immutable
data class ChatMessage(
    val id: Long,
    val text: String,
    val isUser: Boolean
)