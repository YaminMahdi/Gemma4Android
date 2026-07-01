package com.mlab.gemma4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mlab.gemma4.chat.ChatScreen
import com.mlab.gemma4.ui.theme.Gemma4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gemma4Theme {
                ChatScreen()
            }
        }
    }
}