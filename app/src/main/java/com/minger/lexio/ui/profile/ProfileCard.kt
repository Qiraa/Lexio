package com.minger.lexio.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    name: String,
    learningLanguage: String,
    translationLanguage: String,
    languageLevel: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(22.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = name.take(1),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(R.string.languages, learningLanguage, translationLanguage),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                text = languageLevel,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}