package com.minger.lexio.presentation.decks

sealed interface DecksState {

    data class Success(
        val decks: List<Deck>
    ): DecksState {
        data class Deck(
            val deckName: String,
            val deckIcon: String,
            val wordValue: Int,
            val wordToday: Int,
            val learningPercent: Int,
        )
    }
    object Error: DecksState
    object Loading: DecksState
}