package com.minger.lexio.presentation.deck

sealed interface DeckState {

    data class Success(
        val deck: Deck
    ): DeckState {

        data class Deck(
            val deckName: String,
            val deckIcon: String,
            val deckDescription: String,
            val learningPercent: Float,
            val wordValue: Int,
            val cardTodayValue: Int,
            val words: List<Word>
        )
        data class Word(
            val word: String,
            val translation: String,
            val isHardWord: Boolean,
        )
    }

    data object Loading: DeckState

    data object Error: DeckState
}