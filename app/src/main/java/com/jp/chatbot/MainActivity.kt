package com.jp.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.jp.chatbot.ui.theme.ChatBotTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chatViewModel=ViewModelProvider(this)[chatViewModel::class.java]
        setContent {
            ChatBotTheme {
                // A surface container using the 'background' color from the theme
               Scaffold(
                    modifier = Modifier.fillMaxSize()) {innerPadding ->
                        ChatPage(modifier = Modifier.padding(innerPadding),chatViewModel)
                }
            }
        }
    }
}

