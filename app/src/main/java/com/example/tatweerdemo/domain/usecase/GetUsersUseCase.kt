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

class GetUsersUseCase @Inject constructor(
    private val splashRepository: UsersRepository
) {
    suspend fun invoke(pageNumber: Int, size: Int): Flow<List<UserItemResponse>> =
        splashRepository.getUsersList(pageNumber = pageNumber, size = size)
}