package com.mlab.gemma4.chat

sealed class ChatUiState(
    open val isOnGpu: Boolean = false
) {
    data object Idle : ChatUiState()

    data class PreparingModel(
        val progress: Float,
        val statusMessage: String
    ) : ChatUiState()

    data class LoadingModel(
        val statusMessage: String
    ) : ChatUiState()

    data class Ready(
        val messages: List<ChatMessage> = emptyList(),
        val inputText: String = "",
        val isGenerating: Boolean = false,
        override val isOnGpu: Boolean = false,
        val streamingText: String = ""
    ) : ChatUiState(isOnGpu)

    data class Error(
        val message: String
    ) : ChatUiState()
}