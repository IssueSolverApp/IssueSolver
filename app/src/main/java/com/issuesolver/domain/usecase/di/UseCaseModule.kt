package com.issuesolver.domain.usecase.di

import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.ValidateRepeatedPasswordUseCase
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
    fun provideValidatePasswordUseCase()=ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateRepeatedPasswordUseCase() = ValidateRepeatedPasswordUseCase()



}