package com.example.tatweerdemo.data.di

import com.example.tatweerdemo.data.remote.api.UsersApi
import com.example.tatweerdemo.data.repository.UsersRepositoryImpl
import com.example.tatweerdemo.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

@Module(includes = [UsersDataModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object UsersDataModule {

    @Provides
    @Singleton
    fun provideUsersApi(
        retrofit: Retrofit
    ): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {

        @Binds
        @Singleton
        fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
    }
}
