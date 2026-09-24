package com.minger.lexio.ui.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R

@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    cardSide: Int = 0,
    deckLanguage: String,
    translationLanguage: String,
    word: String,
    translation: String,
    onWordChange: (String) -> Unit,
    onTranslationChange: (String) -> Unit,
) {
    val rotation by animateFloatAsState(
        targetValue = cardSide * 180f,
        animationSpec = tween(durationMillis = 400),
        label = "flip",
    )

    val showFront = rotation % 360f !in 90f..270f

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .graphicsLayer {
                    rotationY = if (showFront) 0f else 180f
                }
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            if (showFront) {
                FrontSide(
                    deckLanguage = deckLanguage,
                    word = word,
                    onWordChange = onWordChange,
                )
            } else {
                BackSide(
                    translationLanguage = translationLanguage,
                    translation = translation,
                    onTranslationChange = onTranslationChange,
                )
            }
        }
    }
}

@Composable
private fun FrontSide(
    deckLanguage: String,
    word: String,
    onWordChange: (String) -> Unit,
) {
    Text(
        text = stringResource(
            R.string.word_language, deckLanguage
        ).uppercase(),
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.height(8.dp))
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = word,
        onValueChange = onWordChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = Modifier.fillMaxWidth(),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = word,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_word),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                contentPadding = PaddingValues(0.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
    )
}

@Composable
private fun BackSide(
    translationLanguage: String,
    translation: String,
    onTranslationChange: (String) -> Unit,
) {
    Text(
        text = stringResource(
            R.string.word_translation, translationLanguage
        ).uppercase(),
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.height(8.dp))
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = translation,
        onValueChange = onTranslationChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = Modifier.fillMaxWidth(),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = translation,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_translation),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                },
                contentPadding = PaddingValues(0.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
            )
        },
    )
}
