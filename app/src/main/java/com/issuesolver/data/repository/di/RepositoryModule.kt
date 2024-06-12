package com.issuesolver.data.repository.di

import com.issuesolver.data.repository.LoginRepositoryImpl
import com.issuesolver.data.repository.LoginRepositoryInterface
import com.issuesolver.data.repository.RegisterRepositoryImpl
import com.issuesolver.data.repository.RegisterRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLoginRepository(repository: LoginRepositoryImpl): LoginRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepositoryInterface


}