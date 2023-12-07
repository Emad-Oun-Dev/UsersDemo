package com.example.tatweerdemo.presentation.features.users

import androidx.lifecycle.viewModelScope
import com.example.tatweerdemo.R
import com.example.tatweerdemo.core.base.BaseViewModel
import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse
import com.example.tatweerdemo.domain.usecase.AddNewUserUseCase
import com.example.tatweerdemo.domain.usecase.DeleteUserUseCase
import com.example.tatweerdemo.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val addNewUserUseCase: AddNewUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) :
    BaseViewModel<UsersEvent, UsersState, UsersSideEffects>() {

    init {
        getUsers()
    }

    override fun setInitialState() = UsersState(
        isLoading = false
    )

    private fun getUsers() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getUsersUseCase.invoke(pageNumber = 30, size = 100).catch {
                Timber.d("config_error==${it.message}")
                setState {
                    copy(
                        isLoading = false
                    )
                }
                setEffect {
                    UsersSideEffects.ShowErrorMsg(it.message?:"")
                }
            }.collect { remoteResponse ->
                setState {
                    copy(
                        isLoading = false,
                        usersList = remoteResponse
                    )
                }
            }
        }
    }

    private fun addNewUser(name: String, email: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            addNewUserUseCase.invoke(UserItemResponse(name = name, email = email)).catch {
                Timber.d("config_error==${it.message}")
                setState {
                    copy(
                        isLoading = false
                    )
                }
                setEffect {
                    UsersSideEffects.ShowErrorMsg(it.message?:"")
                }
            }.collect { remoteResponse ->
                Timber.d("name==${remoteResponse.name}")
                getUsers()
                setState {
                    copy(
                        isLoading = false
                    )
                }
                setEffect {
                    UsersSideEffects.ShowSuccessMsg(R.string.add_new_user_successfully)
                }
            }
        }
    }

    private fun deleteUser(userId: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            deleteUserUseCase.invoke(userId = userId).catch {
                Timber.d("config_error==${it.message}")
                setState {
                    copy(
                        isLoading = false
                    )
                }
                setEffect {
                    UsersSideEffects.ShowErrorMsg(it.message?:"")
                }
            }.collect { remoteResponse ->
                Timber.d("name==${remoteResponse}")
                getUsers()
                setState {
                    copy(
                        isLoading = false
                    )
                }
                setEffect {
                    UsersSideEffects.ShowSuccessMsg(R.string.removed_user_successfully)
                }
            }
        }
    }

    override fun handleEvents(event: UsersEvent) {
        when (event) {
            is UsersEvent.AddNewUser -> {
                addNewUser(event.name, event.email)
            }

            is UsersEvent.DeleteUser -> {
                Timber.d("user_id==${event.userId}")
                deleteUser(event.userId)
            }
        }
    }
}