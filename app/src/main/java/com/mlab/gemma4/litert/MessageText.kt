package com.mlab.gemma4.litert

import com.google.ai.edge.litertlm.Content
import com.google.ai.edge.litertlm.Message

internal fun Message.displayText(): String {
    val textParts = contents.contents
        .filterIsInstance<Content.Text>()
        .joinToString(separator = "") { it.text }
    if (textParts.isNotEmpty()) {
        return textParts
    }

    val channelText = channels.values.joinToString(separator = "")
    if (channelText.isNotEmpty()) {
        return channelText
    }

    return toString()
}