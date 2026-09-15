package com.minger.lexio.ui.deck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.deck.DeckState
import com.minger.lexio.presentation.deck.DeckViewModel
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LoadingScreen
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun DeckScreen(
    modifier: Modifier = Modifier,
    viewModel: DeckViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is DeckState.Success -> SuccessContent(
            modifier = modifier,
            onBackClick = {},
            deckIcon = currentState.deck.deckIcon,
            deckName = currentState.deck.deckName,
            deckDescription = currentState.deck.deckDescription,
            learningPercent = currentState.deck.learningPercent,
            cardValueToday = currentState.deck.cardTodayValue,
            wordValue = currentState.deck.wordValue,
            onStartLessonClick = {},
            onRepeatDeckClick = {},
            cards = currentState.deck.words,
            onAddCard = {},
            onEditCardsClick = {},
        )

        DeckState.Loading -> LoadingScreen(modifier = modifier)
        DeckState.Error -> ErrorScreen(modifier = modifier)
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    deckIcon: String,
    deckName: String,
    deckDescription: String,
    learningPercent: Float,
    wordValue: Int,
    cardValueToday: Int,
    onStartLessonClick: () -> Unit,
    onRepeatDeckClick: () -> Unit,
    cards: List<DeckState.Success.Word>,
    onAddCard: () -> Unit,
    onEditCardsClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                TextButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.decks),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp),
                    )
                    Text(
                        text = stringResource(R.string.decks),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text = stringResource(R.string.new_deck),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = deckIcon,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = deckName,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = deckDescription,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                LinearProgressIndicator(
                    progress = { learningPercent },
                    modifier = Modifier.fillMaxWidth(),
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    color = MaterialTheme.colorScheme.surface,
                )
                Text(
                    text = pluralStringResource(
                        R.plurals.deck_info,
                        cardValueToday,
                        (learningPercent * 100).roundToInt(),
                        cardValueToday
                    ),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onStartLessonClick,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(18.dp),
                ) {
                    Text(
                        text = stringResource(R.string.start_learning),
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 25.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = stringResource(R.string.start_learning),
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(20.dp),
                    )
                }
                TextButton(
                    onClick = onRepeatDeckClick,
                ) {
                    Text(
                        text = stringResource(R.string.repeat),
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WordStatsBox(
                    wordValue = wordValue,
                    wordText = stringResource(R.string.all_cards)
                )
                WordStatsBox(
                    wordValue = cardValueToday,
                    wordText = stringResource(R.string.cards_today),
                    valueColor = MaterialTheme.colorScheme.primary,
                )
                WordStatsBox(
                    wordValue = wordValue,
                    wordText = stringResource(R.string.difficult_cards),
                    valueColor = MaterialTheme.colorScheme.error,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.cards),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(
                    onClick = onEditCardsClick,
                ) {
                    Text(
                        text = stringResource(R.string.edit_card),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(cards) { card ->
                    WordCardItem(wordCard = card)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onAddCard,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(18.dp),
            ) {
                Text(
                    text = stringResource(R.string.add_card),
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 25.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }
}