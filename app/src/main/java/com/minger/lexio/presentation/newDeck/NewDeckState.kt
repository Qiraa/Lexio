package com.minger.lexio.presentation.newDeck

sealed interface NewDeckState {

    data class Success(
        val deck: Deck
    ): NewDeckState {
        data class Deck(
            val emoji: String,
            val deckName: String,
            val deckDescription: String,
            val showEmojiPicker: Boolean = false,
            val learningLanguage: String,
            val translationLanguage: String,
        )
    }

    object Loading: NewDeckState

    object Error: NewDeckState

}