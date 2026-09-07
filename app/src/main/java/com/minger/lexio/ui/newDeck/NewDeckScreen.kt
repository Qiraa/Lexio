package com.minger.lexio.ui.newDeck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.minger.lexio.R
import com.minger.lexio.presentation.newDeck.NewDeckState
import com.minger.lexio.presentation.newDeck.NewDeckViewModel
import com.minger.lexio.ui.common.EmojiPicker
import com.minger.lexio.ui.common.ErrorScreen
import com.minger.lexio.ui.common.LanguagePicker
import com.minger.lexio.ui.common.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewDeckScreen(
    modifier: Modifier = Modifier,
    viewModel: NewDeckViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is NewDeckState.Success -> {
            if (currentState.deck.showEmojiPicker) {
                AlertDialog(
                    onDismissRequest = viewModel::onDismissEmojiPicker,
                    text = {
                        EmojiPicker(viewModel::onEmojiSelected)
                    },
                    confirmButton = {},
                )
            }

            SuccessContent(
                modifier = modifier,
                onSaveClick = viewModel::onSaveClick,
                onCancelClick = viewModel::onCancelClick,
                pikedEmoji = currentState.deck.emoji,
                onChangeClick = viewModel::onChangeEmojiClick,
                deckName = currentState.deck.deckName,
                deckDescription = currentState.deck.deckDescription,
                onDeckNameChange = viewModel::onDeckNameChange,
                onDeckDescriptionChange = viewModel::onDeckDescriptionChange,
                learningLanguage = currentState.deck.learningLanguage,
                onLearningLanguageChange = viewModel::onLearningLanguageChange,
                translationLanguage = currentState.deck.translationLanguage,
                onTranslationLanguageChange = viewModel::onTranslationLanguageChange,
            )
        }

        NewDeckState.Loading -> LoadingScreen(modifier = modifier)
        NewDeckState.Error -> ErrorScreen(modifier = modifier)
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    pikedEmoji: String,
    onChangeClick: () -> Unit,
    deckName: String,
    deckDescription: String,
    onDeckNameChange: (String) -> Unit,
    onDeckDescriptionChange: (String) -> Unit,
    learningLanguage: String,
    onLearningLanguageChange: (String) -> Unit,
    translationLanguage: String,
    onTranslationLanguageChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
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
                text = stringResource(R.string.new_deck),
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
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(28.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pikedEmoji,
                    fontSize = 40.sp,
                )
                TextButton(
                    onClick = onChangeClick
                ) {
                    Text(
                        text = stringResource(R.string.choose_the_cover),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
        }
        TextField(
            value = deckName,
            onValueChange = onDeckNameChange,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(R.string.name),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
        )
        TextField(
            value = deckDescription,
            onValueChange = onDeckDescriptionChange,
            label = {
                Text(
                    text = stringResource(R.string.description),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
        )

        LanguagePicker(
            learningLanguage = learningLanguage,
            onLearningLanguageChange = onLearningLanguageChange,
            translationLanguage = translationLanguage,
            onTranslationLanguageChange = onTranslationLanguageChange,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.new_deck_screen_subtitle),
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}