package com.example.tatweerdemo.presentation.features.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tatweerdemo.presentation.features.users.Users
import com.example.tatweerdemo.presentation.features.users.UsersViewModel

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

@Composable
fun UsersScreenDestination() {
    val viewModel = hiltViewModel<UsersViewModel>()
    Users(state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) })
}
