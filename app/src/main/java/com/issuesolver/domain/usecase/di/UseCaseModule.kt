package com.issuesolver.domain.useCase.di

import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.useCase.login.ValidateRepeatedPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideValidateEmailUseCase() = ValidateEmailUseCase()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase()= ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateRepeatedPasswordUseCase() = ValidateRepeatedPasswordUseCase()



}