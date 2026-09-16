package com.minger.lexio.presentation.card

sealed interface NewCardState {

    data class Success(
        val deckLanguage: String,
        val deckTranslation: String,
        val word: String,
        val translation: String,
        val example: String,
    ): NewCardState

    data object Error: NewCardState

    data object Loading: NewCardState
}
