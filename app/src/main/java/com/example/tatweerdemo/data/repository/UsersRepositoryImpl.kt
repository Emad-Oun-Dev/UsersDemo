package com.example.tatweerdemo.data.repository

import com.example.tatweerdemo.core.base.BaseRepo
import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse
import com.example.tatweerdemo.data.remote.api.UsersApi
import com.example.tatweerdemo.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
) : UsersRepository, BaseRepo() {
    override suspend fun getUsersList(pageNumber: Int, size: Int): Flow<List<UserItemResponse>> {
        return flow {
            safeApiCall {
                usersApi.getUsersList(pageNumber = pageNumber, size = size)
            }.data?.let { emit(it) }
        }
    }

    override suspend fun addNewUser(item: UserItemResponse): Flow<UserItemResponse> {
        return flow {
            safeApiCall {
                usersApi.addNewUser(item = item)
            }.data?.let { emit(it) }
        }
    }

    override suspend fun deleteUser(userId: String): Flow<Any> {
        return flow {
            emit(usersApi.deleteUser(userId = userId))
        }
    }
}
