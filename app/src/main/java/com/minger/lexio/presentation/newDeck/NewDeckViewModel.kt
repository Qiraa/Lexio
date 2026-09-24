package com.minger.lexio.presentation.newDeck

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewDeckViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<NewDeckState> = MutableStateFlow(
        NewDeckState.Success(
            deck = NewDeckState.Success.Deck(
                emoji = "\uD83C\uDF0F",
                deckName = "",
                deckDescription = "",
                learningLanguage = "",
                translationLanguage = "",
            )
        )
    )
    val state: StateFlow<NewDeckState> = mutableState.asStateFlow()

    fun onEmojiSelected(emoji: String) {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(
                        emoji = emoji,
                        showEmojiPicker = false,
                    )
                )
            } else currentState
        }
    }

    fun onChangeEmojiClick() {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(showEmojiPicker = true)
                )
            } else currentState
        }
    }

    fun onDismissEmojiPicker() {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(showEmojiPicker = false)
                )
            } else currentState
        }
    }

    fun onDeckNameChange(name: String) {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(deckName = name)
                )
            } else currentState
        }
    }

    fun onDeckDescriptionChange(description: String) {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(deckDescription = description)
                )
            } else currentState
        }
    }

    fun onLearningLanguageChange(learningLanguage: String) {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(learningLanguage = learningLanguage)
                )
            } else currentState
        }
    }

    fun onTranslationLanguageChange(translationLanguage: String) {
        mutableState.update { currentState ->
            if (currentState is NewDeckState.Success) {
                currentState.copy(
                    deck = currentState.deck.copy(translationLanguage = translationLanguage)
                )
            } else currentState
        }
    }

    fun onCancelClick() {
    }

    fun onSaveClick() {
    }
}