package com.minger.lexio.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun StreakCard(
    modifier: Modifier = Modifier,
    streakDays: Int,
    bestStreakDays: Int,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.current_series).uppercase(),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.surface,
            )
            Column {
                Row {
                    Text(
                        text = stringResource(R.string.fire_emoji),
                        fontSize = 34.sp,
                    )
                    Text(
                        text = pluralStringResource(R.plurals.days, streakDays, streakDays),
                        fontSize = 34.sp,
                        color = MaterialTheme.colorScheme.surface,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(R.string.the_best_series),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.surface,
                )
                Text(
                    text = pluralStringResource(R.plurals.days, bestStreakDays, bestStreakDays),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.surface,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = stringResource(R.string.week),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}