package com.minger.lexio.ui.decks

data class Deck (
    val deckName: String,
    val deckIcon: String,
    val wordValue: Int,
    val wordToday: Int,
    val learningPercent: Int,
)
