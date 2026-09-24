package com.minger.lexio.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.profile.ProfileState
import com.minger.lexio.presentation.profile.ProfileViewModel
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LoadingScreen
import com.minger.lexio.ui.deck.WordStatsBox
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        ProfileState.Success -> SuccessContent(
            modifier = modifier,
            onEditProfileClick = {},
            cardsValue = 100,
            accuracy = 58,
            time = 1,
            weeklyCardValue = 100,
            daysValue = 4,
            onActivityClick = {}
        )

        ProfileState.Error -> ErrorScreen(modifier = modifier)
        ProfileState.Loading -> LoadingScreen(modifier = modifier)
    }

}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit,
    cardsValue: Int,
    accuracy: Int,
    time: Int,
    weeklyCardValue: Int,
    daysValue: Int,
    onActivityClick: () -> Unit,
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
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.profile_screen_title),
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                )
                FloatingActionButton(
                    onClick = onEditProfileClick,
                    modifier = Modifier.size(50.dp),
                    shape = RoundedCornerShape(15.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = stringResource(R.string.add_new_deck),
                        modifier = Modifier.size(25.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            ProfileCard(
                name = "Daniil",
                learningLanguage = "English",
                translationLanguage = "Russian",
                languageLevel = "B1"
            )
            StreakCard(
                streakDays = 12,
                bestStreakDays = 20,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WordStatsBox(
                    value = cardsValue.toString(),
                    wordText = stringResource(R.string.cards)
                )
                WordStatsBox(
                    value = "$accuracy %",
                    wordText = stringResource(R.string.accuracy),
                    valueColor = MaterialTheme.colorScheme.primary,
                )
                WordStatsBox(
                    value = stringResource(R.string.hours, time, time),
                    wordText = stringResource(R.string.time),
                    valueColor = MaterialTheme.colorScheme.error,
                )
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
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
                        Icon(
                            imageVector = Icons.Default.ArrowOutward,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(30.dp),
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.weekly_activity),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                        )
                        Row {
                            Text(
                                text = weeklyCardValue.toString(),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                            Text(
                                text = pluralStringResource(R.plurals.daysOf, daysValue, daysValue),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                    IconButton(
                        onClick = onActivityClick,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }
        }
    }
}
