package com.example.tatweerdemo.presentation.features.users

import com.example.tatweerdemo.core.base.ViewState
import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

data class UsersState(
    val isLoading: Boolean,
    val usersList: List<UserItemResponse> = emptyList()
) : ViewState
