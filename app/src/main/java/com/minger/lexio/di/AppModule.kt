package com.minger.lexio.di

import com.minger.lexio.presentation.card.NewCardViewModel
import com.minger.lexio.presentation.deck.DeckViewModel
import com.minger.lexio.presentation.decks.DecksViewModel
import com.minger.lexio.presentation.main.MainViewModel
import com.minger.lexio.presentation.newDeck.NewDeckViewModel
import com.minger.lexio.presentation.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        DecksViewModel()
    }
    viewModel {
        NewDeckViewModel()
    }
     viewModel {
         DeckViewModel()
     }
    viewModel {
        NewCardViewModel()
    }
    viewModel {
        ProfileViewModel()
    }
}
