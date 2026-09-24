package com.minger.lexio.presentation.decks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DecksViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<DecksState> = MutableStateFlow(
        DecksState.Success(
            decks = listOf(
                DecksState.Success.Deck(
                    deckName = "Страны",
                    deckIcon = "\uD83C\uDDE9\uD83C\uDDF0",
                    wordValue = 50,
                    wordToday = 10,
                    learningPercent = 80,
                ),
                DecksState.Success.Deck(
                    deckName = "Животные",
                    deckIcon = "\uD83D\uDC36",
                    wordValue = 10,
                    wordToday = 8,
                    learningPercent = 20,
                ),
                DecksState.Success.Deck(
                    deckName = "Путешествия",
                    deckIcon = "\uD83C\uDF07",
                    wordValue = 100,
                    wordToday = 50,
                    learningPercent = 50,
                )
            ),
            query = ""
        )
    )
    val state: StateFlow<DecksState> = mutableState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        mutableState.update { currentState ->
            if (currentState is DecksState.Success) {
                currentState.copy(query = query)
            } else currentState
        }
        viewModelScope.launch {

        }
    }
}
