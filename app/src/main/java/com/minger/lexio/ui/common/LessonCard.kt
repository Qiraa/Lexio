package com.minger.lexio.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    cardValue: Int,
    newCardValue: Int,
    iterationCardValue: Int,
    cardProgress: Float,
    onStartLessonClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(28.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.today_plan),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.surface,
            )
            Text(
                text = pluralStringResource(R.plurals.word_cards, cardValue, cardValue),
                textAlign = TextAlign.Start,
                fontSize = 34.sp,
                color = MaterialTheme.colorScheme.surface,
            )
            Text(
                text = pluralStringResource(
                    R.plurals.new_words_card,
                    newCardValue,
                    newCardValue,
                    iterationCardValue
                ),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.surface,
            )
            LinearProgressIndicator(
                progress = { cardProgress },
                modifier = Modifier.fillMaxWidth(),
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.surface,
            )
            Button(
                onClick = onStartLessonClick,
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    vertical = 16.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.start_lesson),
                    textAlign = TextAlign.Center,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
