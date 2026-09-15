package com.minger.lexio.ui.card

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.card.NewCardState
import com.minger.lexio.presentation.card.NewCardViewModel
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        is NewCardState.Success -> SuccessContent(
            modifier = modifier,
            onCancelClick = {},
            onSaveClick = {},
            deckLanguage = currentState.deckLanguage,
            deckTranslation = currentState.deckTranslation,
            word = currentState.word,
            translation = currentState.translation,
            onWordChange = viewModel::onWordChange,
            onTranslationChange = viewModel::onTranslationChange,
            exampleText = currentState.example,
            onExampleTextChange = viewModel::onExampleChange,
            onAddCard = {},
        )

        NewCardState.Error -> ErrorScreen(modifier = modifier)
        NewCardState.Loading -> LoadingScreen(modifier = modifier)
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    deckLanguage: String,
    deckTranslation: String,
    word: String,
    translation: String,
    onWordChange: (String) -> Unit,
    onTranslationChange: (String) -> Unit,
    exampleText: String,
    onExampleTextChange: (String) -> Unit,
    onAddCard: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    onClick = onCancelClick,
                ) {
                    Text(
                        text = stringResource(R.string.canсel),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text = stringResource(R.string.new_card),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextButton(
                    onClick = onSaveClick,
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            var selected by remember { mutableIntStateOf(0) }
            CardSideSwitcher(
                selected = selected,
                onSelectedChange = { selected = it }
            )

            WordCard(
                cardSide = selected,
                deckLanguage = deckLanguage,
                translationLanguage = deckTranslation,
                word = word,
                translation = translation,
                onWordChange = onWordChange,
                onTranslationChange = onTranslationChange,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.example).uppercase(),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            val exampleInteractionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = exampleText,
                onValueChange = onExampleTextChange,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                modifier = Modifier.fillMaxWidth(),
                interactionSource = exampleInteractionSource,
                decorationBox = { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = exampleText,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = exampleInteractionSource,
                        placeholder = {
                            Text(
                                text = stringResource(R.string.enter_example),
                                fontSize = 18.sp,
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
        Button(
            onClick = onAddCard,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
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
