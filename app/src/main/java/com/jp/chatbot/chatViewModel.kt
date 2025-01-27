package com.jp.chatbot


import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch


class chatViewModel:ViewModel() {

    val messageList by lazy {
       mutableStateListOf<MessageModel>()
    }

    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash-001",
        apiKey = Constants.apiKey
    )

    fun sendMessage(question: String) {
        viewModelScope.launch {
            val chat = generativeModel.startChat(
                history = messageList.map {
                    content(it.role){ text (it.message)}
                }.toList()
            )

            messageList.add(MessageModel(question, "user"))
            messageList.add(MessageModel("Typing...","model"))
            val response = chat.sendMessage(question)
            messageList.removeLast()
            messageList.add(MessageModel(response.text.toString(), "model"))

        }
    }
}