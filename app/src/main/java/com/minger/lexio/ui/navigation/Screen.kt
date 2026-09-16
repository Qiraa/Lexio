package com.minger.lexio.ui.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Decks : Screen("decks")
    object Profile : Screen("profile")
    object NewDeck : Screen("new_deck")

    object Deck : Screen("deck")

    object NewCard: Screen("new_card")
}

val bottomBarScreens = listOf(
    Screen.Main.route,
    Screen.Decks.route,
    Screen.Profile.route,
)
