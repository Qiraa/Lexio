package com.minger.lexio.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.ui.decks.DeckCard

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    userName: String = "Даниил",
    onAllDecksClick: () -> Unit = {},
    onDeckClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.good_morning, userName),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(R.string.main_screen_subtitle),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = userName.take(1),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
            LessonCard(
                cardValue = "12 карточек",
                newCardValue = "5 новых",
                iterationCardValue = "7 на повторение",
                onStartLessonClick = { },
                cardProgress = 0.5f,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.recent_deck),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(
                    onClick = onAllDecksClick,
                ) {
                    Text(
                        text = stringResource(R.string.all_decks),
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            DeckCard(
                onDeckClick = onDeckClick,
                emojiIcon = "\uD83C\uDDE9\uD83C\uDDF0",
                deckName = "Еда",
                wordsValue = "40 слов",
                learningPercent = "42% изучено",
            )
        }
    }
}
