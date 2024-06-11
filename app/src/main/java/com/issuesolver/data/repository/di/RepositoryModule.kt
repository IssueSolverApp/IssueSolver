package com.issuesolver.data.repository.di

import com.issuesolver.data.repository.LoginRepositoryImpl
import com.issuesolver.data.repository.LoginRepositoryInterface
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
    abstract fun provideBurgerRepository(repository: LoginRepositoryImpl): LoginRepositoryInterface


}