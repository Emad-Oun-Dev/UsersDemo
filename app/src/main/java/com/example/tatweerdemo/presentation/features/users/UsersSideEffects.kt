package com.example.tatweerdemo.presentation.features.users

import com.example.tatweerdemo.core.base.ViewSideEffect

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

sealed class UsersSideEffects : ViewSideEffect {
    data class ShowErrorMsg(val msg: String) : UsersSideEffects()
    data class ShowSuccessMsg(val msg: Int) : UsersSideEffects()
}
