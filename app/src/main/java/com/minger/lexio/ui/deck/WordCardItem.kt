package com.minger.lexio.ui.deck

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.minger.lexio.presentation.deck.DeckState

@Composable
fun WordCardItem(
    modifier: Modifier = Modifier,
    wordCard: DeckState.Success.Word,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = wordCard.word,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = wordCard.translation,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}