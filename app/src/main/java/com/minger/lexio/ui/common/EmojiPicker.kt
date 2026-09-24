package com.minger.lexio.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView

@Composable
fun EmojiPicker(onEmojiSelected: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            EmojiPickerView(context).apply {
                setOnEmojiPickedListener { item ->
                    onEmojiSelected(item.emoji)
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
