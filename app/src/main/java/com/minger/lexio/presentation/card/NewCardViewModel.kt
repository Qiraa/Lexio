package com.minger.lexio.presentation.card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewCardViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<NewCardState> = MutableStateFlow(
        NewCardState.Success(
            deckLanguage = "English",
            deckTranslation = "Русский",
            word = "meat",
            translation = "мясо",
            example = "Свинина, курица и говядина - разные виды мяса",
        )
    )
    val state: StateFlow<NewCardState> = mutableState.asStateFlow()

    fun onWordChange(word: String) {
        mutableState.update { currentState ->
            if (currentState is NewCardState.Success) {
                currentState.copy(word = word)
            } else currentState
        }
    }
    fun onTranslationChange(translation: String) {
        mutableState.update { currentState ->
            if (currentState is NewCardState.Success) {
                currentState.copy(translation = translation)
            } else currentState
        }
    }
    fun onExampleChange(example: String) {
        mutableState.update { currentState ->
            if (currentState is NewCardState.Success) {
                currentState.copy(example = example)
            } else currentState
        }
    }
}
