package com.example.tatweerdemo.domain.repository

import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

interface UsersRepository {
  suspend fun getUsersList(pageNumber: Int, size: Int): Flow<List<UserItemResponse>>
  suspend fun addNewUser(item: UserItemResponse): Flow<UserItemResponse>
  suspend fun deleteUser(userId: String): Flow<Any>
}