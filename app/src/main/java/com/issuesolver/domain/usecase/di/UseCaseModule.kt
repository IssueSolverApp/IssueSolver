package com.issuesolver.domain.useCase.di

import com.issuesolver.data.repository.ConfirmOtpRepositoryInterface
import com.issuesolver.data.repository.RegisterRepositoryInterface
import com.issuesolver.data.repository.ResendOtpRepositoryInterface
import com.issuesolver.domain.useCase.ConfirmOtpUseCase
import com.issuesolver.domain.useCase.RegisterUseCase
import com.issuesolver.domain.useCase.ResendOtpUseCase
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
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateRepeatedPasswordUseCase() = ValidateRepeatedPasswordUseCase()

    @Provides
    @Singleton
    fun provideConfirmOtpUseCase(confirmOtpRepository: ConfirmOtpRepositoryInterface) =
        ConfirmOtpUseCase(confirmOtpRepository)


    @Provides
    @Singleton
    fun provideRegisterUseCase(registerRepository: RegisterRepositoryInterface) =
        RegisterUseCase(registerRepository)


    @Provides
    @Singleton
    fun provideResendOtpUseCase(resendOtpRepository: ResendOtpRepositoryInterface) =
        ResendOtpUseCase(resendOtpRepository)


}