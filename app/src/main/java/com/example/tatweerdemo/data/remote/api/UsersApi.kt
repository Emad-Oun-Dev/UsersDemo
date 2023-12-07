package com.example.tatweerdemo.data.remote.api

import com.example.tatweerdemo.data.DELETE_USER
import com.example.tatweerdemo.data.USERS_LIST
import com.example.tatweerdemo.data.remote.model.responses.UserItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

interface UsersApi {
    @GET(USERS_LIST)
    suspend fun getUsersList(@Query("page")  pageNumber: Int,
                             @Query("per_page") size: Int): Response<List<UserItemResponse>>

    @POST(USERS_LIST)
    suspend fun addNewUser(@Body item: UserItemResponse): Response<UserItemResponse>

    @DELETE(DELETE_USER)
    suspend fun deleteUser(@Path("user_id") userId: String): Response<Any>
}
