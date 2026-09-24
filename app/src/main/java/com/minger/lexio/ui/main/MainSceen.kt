package com.minger.lexio.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.main.MainState
import com.minger.lexio.presentation.main.MainViewModel
import com.minger.lexio.ui.common.LessonCard
import com.minger.lexio.ui.common.DeckCard
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onAllDecksClick: () -> Unit = {},
    onDeckClick: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        MainState.Loading -> LoadingScreen(modifier = modifier)
        MainState.Error -> ErrorScreen(modifier = modifier)
        is MainState.Success -> SuccessContent(
            modifier = modifier,
            currentState = currentState,
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    currentState: MainState.Success,
    onStartLessonClick: () -> Unit = {},
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
                        text = currentState.userName?.let {
                            stringResource(
                                R.string.good_morning, it
                            )
                        } ?: stringResource(R.string.user),
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
                        text = currentState.userName?.take(1) ?: stringResource(R.string.user),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
            if (currentState.todayLesson != null) {
                LessonCard(
                    cardValue = currentState.todayLesson.deckValue,
                    newCardValue = currentState.todayLesson.newCardValue,
                    iterationCardValue = currentState.todayLesson.iterationCardValue,
                    onStartLessonClick = onStartLessonClick,
                    cardProgress = 0.3f,
                )
            }
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
            if (currentState.recentDeck != null) {
                DeckCard(
                    onDeckClick = onDeckClick,
                    emojiIcon = currentState.recentDeck.deckIcon,
                    deckName = currentState.recentDeck.deckName,
                    wordsValue = currentState.recentDeck.wordValue,
                    learningPercent = currentState.recentDeck.learningPercent,
                )
            }
        }
    }
}
