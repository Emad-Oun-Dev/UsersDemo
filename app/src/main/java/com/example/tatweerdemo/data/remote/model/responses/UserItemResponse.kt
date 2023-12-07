package com.example.tatweerdemo.data.remote.model.responses

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

data class UserItemResponse(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val gender: String? = "male",
    val status: String? = "active"
)