package com.minger.lexio.presentation.main

sealed interface MainState {

    data class Success(
        val userName: String?,
        val todayLesson: Lesson?,
        val recentDeck: Deck?,
    ): MainState {

        data class Deck(
            val deckName: String,
            val deckIcon: String,
            val wordValue: Int,
            val learningPercent: Int,
        )

        data class Lesson(
            val deckValue: Int,
            val newCardValue: Int,
            val iterationCardValue: Int,
        )
    }
    data object Error: MainState
    data object Loading: MainState
}
