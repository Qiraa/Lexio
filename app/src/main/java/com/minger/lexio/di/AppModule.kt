package com.minger.lexio.di

import com.minger.lexio.presentation.decks.DecksViewModel
import com.minger.lexio.presentation.main.MainViewModel
import com.minger.lexio.presentation.newDeck.NewDeckViewModel
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
}
