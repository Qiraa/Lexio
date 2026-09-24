package com.minger.lexio.presentation.profile

sealed interface ProfileState{

    data object Success: ProfileState

    data object Error: ProfileState

    data object Loading: ProfileState
}