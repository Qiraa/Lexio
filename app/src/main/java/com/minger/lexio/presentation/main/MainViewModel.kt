package com.minger.lexio.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val mutableState: MutableStateFlow<MainState> =
        MutableStateFlow(
            MainState.Success(
                userName = "Даниил",
                todayLesson = MainState.Success.Lesson(
                    12, 7, 3),
                recentDeck = MainState.Success.Deck(
                    "Страны", "\uD83C\uDDE9\uD83C\uDDF0", 32, 25)
            )
        )
    val state: StateFlow<MainState> = mutableState.asStateFlow()

}