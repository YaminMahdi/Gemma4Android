package com.mlab.gemma4.litert

import android.content.Context
import com.google.ai.edge.litertlm.Backend
import com.google.ai.edge.litertlm.Content
import com.google.ai.edge.litertlm.Contents
import com.google.ai.edge.litertlm.Conversation
import com.google.ai.edge.litertlm.ConversationConfig
import com.google.ai.edge.litertlm.Engine
import com.google.ai.edge.litertlm.EngineConfig
import com.google.ai.edge.litertlm.ExperimentalApi
import com.google.ai.edge.litertlm.ExperimentalFlags
import com.google.ai.edge.litertlm.LogSeverity
import com.google.ai.edge.litertlm.Message
import com.google.ai.edge.litertlm.SamplerConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LiteRtChatSession private constructor(
    private val engine: Engine,
    private val conversation: Conversation,
    val isOnGpu: Boolean
) : AutoCloseable {

    fun sendMessageAsync(prompt: String): Flow<Message> =
        conversation.sendMessageAsync(prompt)

    override fun close() {
        conversation.close()
        engine.close()
    }

    companion object {

        @OptIn(ExperimentalApi::class)
        suspend fun create(
            context: Context,
            modelPath: String,
        ): LiteRtChatSession = withContext(Dispatchers.IO) {
            Engine.setNativeMinLogSeverity(LogSeverity.ERROR)
            var isOnGpu: Boolean
            val engine = try {
                ExperimentalFlags.enableSpeculativeDecoding = true
                Engine(
                    EngineConfig(
                        modelPath = modelPath,
                        backend = Backend.GPU(),
                        cacheDir = context.cacheDir.path,
                    ),
                ).also {
                    it.initialize()
                    isOnGpu = true
                }
            } catch (_: Throwable) {
                Engine(
                    EngineConfig(
                        modelPath = modelPath,
                        backend = Backend.CPU(),
                        cacheDir = context.cacheDir.path,
                    ),
                ).also {
                    it.initialize()
                    isOnGpu = false
                }
            }

            val conversationConfig = ConversationConfig(
                systemInstruction = Contents.of(
                    listOf(
                        Content.Text(KitContext.kitBdAIAssistantPersonality),
                        Content.Text(KitContext.kitBdKnowledgeBase)
                    )
                ),
                initialMessages = listOf(Message.system(
                """
                    KIT BD AI Assistant online.
                    Ready to support backend, frontend, and system design tasks.
                    Ask your first technical question.
                    """.trimIndent()
                )),
                samplerConfig = SamplerConfig(
                    topK = 40,
                    topP = 0.95,
                    temperature = 0.8
                ),
                extraContext = KitContext.extraContext
            )
            val conversation = engine.createConversation(conversationConfig)
            LiteRtChatSession(engine, conversation, isOnGpu)
        }
    }
}