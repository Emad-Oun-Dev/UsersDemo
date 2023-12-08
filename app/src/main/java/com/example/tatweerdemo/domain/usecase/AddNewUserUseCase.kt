package com.example.tatweerdemo.domain.usecase

import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse
import com.example.tatweerdemo.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

class AddNewUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun invoke(item: UserItemResponse): Flow<UserItemResponse> =
        usersRepository.addNewUser(item = item)
}