package com.minger.lexio.presentation.deck

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeckViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<DeckState> = MutableStateFlow(
        DeckState.Success(
            deck = DeckState.Success.Deck(
                deckName = "Еда",
                deckIcon = "\uD83C\uDF4E",
                deckDescription = "Еда, виды, способы приготовления",
                learningPercent = 0.5f,
                wordValue = 10,
                cardTodayValue = 5,
                words = listOf(
                    DeckState.Success.Word(
                        word = "meat",
                        translation = "мясо",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "chicken",
                        translation = "курица",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "potato",
                        translation = "картофель",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "pork",
                        translation = "свинина",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "tomato",
                        translation = "томат",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "melon",
                        translation = "дыня",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "watermelon",
                        translation = "арбуз",
                        isHardWord = false,
                    ),
                    DeckState.Success.Word(
                        word = "cucumber",
                        translation = "огурец",
                        isHardWord = false,
                    ),
                ),
            )
        )
    )

    val state: StateFlow<DeckState> = mutableState.asStateFlow()
}