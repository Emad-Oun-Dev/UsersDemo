package com.example.tatweerdemo.presentation.features.users

import com.example.tatweerdemo.core.base.ViewEvent


/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */
sealed class UsersEvent : ViewEvent {
    data class AddNewUser(val name: String, val email: String) : UsersEvent()
    data class DeleteUser(val userId: String) : UsersEvent()

}
