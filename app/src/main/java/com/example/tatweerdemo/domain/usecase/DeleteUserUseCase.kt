package com.example.tatweerdemo.domain.usecase

import com.example.tatweerdemo.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

class DeleteUserUseCase @Inject constructor(
    private val splashRepository: UsersRepository
) {
    suspend fun invoke(userId: String): Flow<Any> =
        splashRepository.deleteUser(userId = userId)
}