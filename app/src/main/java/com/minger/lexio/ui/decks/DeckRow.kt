package com.minger.lexio.ui.decks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.ui.theme.BadResult
import com.minger.lexio.ui.theme.BestResult
import com.minger.lexio.ui.theme.GoodResult
import com.minger.lexio.ui.theme.MiddleResult

@Composable
fun DeckRow(
    modifier: Modifier = Modifier,
    deck: Deck,
    onDeckClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = onDeckClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = deck.deckIcon,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = deck.deckName,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = pluralStringResource(
                            R.plurals.words_value,
                            deck.wordValue,
                            deck.wordValue,
                            deck.learningPercent,
                        ),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "•",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    if (deck.learningPercent != 100) {
                        Text(
                            text = stringResource(R.string.words_today, deck.wordToday),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.everything_repeated),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
            val textColor = when (deck.learningPercent) {
                in 0..25 -> BadResult
                in 26..50 -> MiddleResult
                in 51..85 -> GoodResult
                else -> BestResult
            }
            Text(
                text = stringResource(R.string.learning_percent, deck.learningPercent),
                fontSize = 18.sp,
                color = textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}
