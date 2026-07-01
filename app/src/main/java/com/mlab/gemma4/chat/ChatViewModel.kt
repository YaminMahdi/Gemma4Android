package com.mlab.gemma4.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mlab.gemma4.litert.LiteRtChatSession
import com.mlab.gemma4.litert.ModelPreparer
import com.mlab.gemma4.litert.displayText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    val uiState: StateFlow<ChatUiState>
        field = MutableStateFlow<ChatUiState>(ChatUiState.Idle)

    private var session: LiteRtChatSession? = null
    private var nextMessageId = 0L

    init {
        initialize()
    }

    fun initialize() {
        if (uiState.value !is ChatUiState.Idle && uiState.value !is ChatUiState.Error) {
            return
        }

        viewModelScope.launch {
            try {
                uiState.value = ChatUiState.PreparingModel(
                    progress = 0f,
                    statusMessage = "Preparing model files…",
                )

                val modelPath = ModelPreparer.prepareModel(application) { progress ->
                    uiState.value = ChatUiState.PreparingModel(
                        progress = progress,
                        statusMessage = if (progress < 1f) {
                            "Copying model to device storage…"
                        } else {
                            "Model files ready"
                        },
                    )
                }

                uiState.value = ChatUiState.LoadingModel(
                    statusMessage = "Loading Gemma 4 (this may take up to a minute)…",
                )

                session = LiteRtChatSession.create(getApplication(), modelPath)
                uiState.value = ChatUiState.Ready(isOnGpu = session?.isOnGpu ?: false)
            } catch (error: Throwable) {
                session?.close()
                session = null
                uiState.value = ChatUiState.Error(
                    message = error.message ?: "Failed to initialize LiteRT-LM",
                )
            }
        }
    }

    fun updateInput(text: String) {
        val current = uiState.value
        if (current is ChatUiState.Ready && !current.isGenerating) {
            uiState.value = current.copy(inputText = text)
        }
    }

    fun sendMessage() {
        val current = uiState.value as? ChatUiState.Ready ?: return
        val prompt = current.inputText.trim()
        if (prompt.isEmpty() || current.isGenerating) return

        val activeSession = session ?: run {
            uiState.value = ChatUiState.Error("Chat session is not available.")
            return
        }

        val userMessage = ChatMessage(
            id = nextMessageId++,
            text = prompt,
            isUser = true,
        )
        uiState.value = current.copy(
            messages = current.messages + userMessage,
            inputText = "",
            isGenerating = true,
            streamingText = "",
        )

        viewModelScope.launch {
            try {
                var responseText = ""
                activeSession.sendMessageAsync(prompt)
                    .catch { error ->
                        throw error
                    }
                    .collect { chunk ->
                        responseText += chunk.displayText()
                        val ready = uiState.value as? ChatUiState.Ready ?: return@collect
                        uiState.value = ready.copy(streamingText = responseText)
                    }

                uiState.update { state ->
                    val ready = state as? ChatUiState.Ready ?: return@update state
                    ready.copy(
                        messages = ready.messages + ChatMessage(
                            id = nextMessageId++,
                            text = responseText.ifEmpty { "No response generated." },
                            isUser = false,
                        ),
                        isGenerating = false,
                        streamingText = ""
                    )
                }
            } catch (error: Throwable) {
                uiState.update { state ->
                    val ready = state as? ChatUiState.Ready ?: return@update ChatUiState.Error(
                        message = error.message ?: "Failed to generate a response",
                    )
                    ready.copy(
                        messages = ready.messages + ChatMessage(
                            id = nextMessageId++,
                            text = "Error: ${error.message ?: "Unknown error"}",
                            isUser = false,
                        ),
                        isGenerating = false,
                        streamingText = ""
                    )
                }
            }
        }
    }

    override fun onCleared() {
        session?.close()
        session = null
    }
}