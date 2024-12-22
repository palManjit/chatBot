package com.jp.chatbot

import android.content.ClipData.Item
import android.text.Selection
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jp.chatbot.ui.theme.ColorModelMessage
import com.jp.chatbot.ui.theme.ColorUserMessage

@Composable
fun ChatPage(modifier: Modifier = Modifier, viewModel: chatViewModel) {
    Column(modifier = Modifier) {
        AppHeader()

        MessageList(
            modifier = Modifier.weight(1f), messageList = viewModel.messageList
        )

        InputMessage(onMassege = {
            viewModel.sendMessage(it)
        })
    }

}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Text(
            text = "chatBot",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 20.sp, color = Color.White)
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputMessage(onMassege: (String) -> Unit) {

    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(modifier = Modifier
            .weight(1f)
            .padding(10.dp)
            .clip(RoundedCornerShape(10f)),
            value = message, onValueChange = {
                message = it

            })

        IconButton(onClick = {
            onMassege(message)
            message = ""
        }) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "send")

        }
    }

}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    LazyColumn(
        modifier = modifier.padding(10.dp),
        reverseLayout = true
    ) {
        items(messageList.reversed()) {
            MessageRow(messageModel = it)
        }
    }

}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(
                        start = if (isModel) 8.dp else 70.dp,
                        end = if (isModel) 70.dp else 8.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(RoundedCornerShape(48f))
                    .background(if (isModel) ColorModelMessage else ColorUserMessage)
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(
                        text = messageModel.message,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }


            }
        }
    }

}