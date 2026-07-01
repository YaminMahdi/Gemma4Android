package com.mlab.gemma4.chat.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mlab.gemma4.R
import com.mlab.gemma4.chat.ChatSpacing
import com.mlab.gemma4.ui.theme.Gemma4Theme

@Composable
fun ChatInputBar(
    inputText: String,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
    enabled: Boolean,
    canSend: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    tonalElevation: Dp = ChatSpacing.xs,
    shadowElevation: Dp = 0.dp
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = containerColor,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    ) {
        val sendButtonScale by animateFloatAsState(
            targetValue = if (canSend) 1f else 0.85f,
            animationSpec = spring(dampingRatio = 0.6f),
            label = "send_button_scale",
        )

        OutlinedTextField(
            value = inputText,
            onValueChange = onInputChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(ChatSpacing.md),
            enabled = enabled,
            placeholder = { Text(stringResource(R.string.chat_input_hint)) },
            singleLine = false,
            maxLines = 4,
            shape = RoundedCornerShape(24.dp),
            // Filled pill with a border that only appears on focus, rather
            // than a permanently outlined box — reads as one continuous
            // composer surface instead of a field floating inside a bar.
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
            ),
            // Return key sends instead of inserting a newline, matching how
            // most chat composers behave on soft keyboards; maxLines = 4
            // still lets long messages wrap and grow.
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = { if (canSend) onSend() },
            ),
            trailingIcon = {
                FilledIconButton(
                    onClick = onSend,
                    enabled = canSend,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .height(36.dp)
                        .width(65.dp)
                        .padding(end = 10.dp)
                        .scale(sendButtonScale),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Send,
                            contentDescription = stringResource(R.string.send),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(start = 4.dp)
                        )
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true, name = "Default")
@Composable
private fun ChatInputBarPreview() {
    Gemma4Theme {
        ChatInputBar(
            inputText = "Hello Gemma",
            onInputChange = {},
            onSend = {},
            enabled = true,
            canSend = true,
        )
    }
}

@Preview(showBackground = true, name = "Floating composer")
@Composable
private fun ChatInputBarFloatingPreview() {
    Gemma4Theme {
        ChatInputBar(
            inputText = "",
            onInputChange = {},
            onSend = {},
            enabled = true,
            canSend = false,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 6.dp,
            shadowElevation = 8.dp,
        )
    }
}