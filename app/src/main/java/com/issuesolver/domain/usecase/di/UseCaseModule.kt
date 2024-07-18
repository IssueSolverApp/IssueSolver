package com.issuesolver.domain.usecase.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.issuesolver.data.repository.ConfirmOtpRepositoryInterface
import com.issuesolver.data.repository.OtpTrustRepositoryInterface
import com.issuesolver.data.repository.RegisterRepositoryInterface
import com.issuesolver.data.repository.ResendOtpRepositoryInterface
import com.issuesolver.data.repository.ResetPasswordRepositoryInterface
import com.issuesolver.data.repository.SignInRepositoryInterface
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryInterface
import com.issuesolver.domain.usecase.ConfirmOtpUseCase
import com.issuesolver.domain.usecase.OtpTrustUseCase
import com.issuesolver.domain.usecase.RegisterUseCase

import com.issuesolver.domain.usecase.ResendOtpUseCase
import com.issuesolver.domain.usecase.ResetPasswordUseCase
import com.issuesolver.domain.usecase.SignInUseCase
import com.issuesolver.domain.usecase.login.ValidateFullNameUseCase
import com.issuesolver.domain.usecase.login.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.LoginUseCase
import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidateNewPasswordUseCase

import com.issuesolver.domain.usecase.login.ValidateRepeatedPasswordUseCase
import com.issuesolver.domain.usecase.newrequestusecase.NewRequestUseCase

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
    fun provideLoginUseCase() = LoginUseCase()

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateNewPasswordUseCase() = ValidateNewPasswordUseCase()

    @Provides
    @Singleton
    fun provideValidateFullNameUseCase() = ValidateFullNameUseCase()

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

    @Provides
    @Singleton
    fun provideSignInUseCase(
        signInRepository: SignInRepositoryInterface,
        sharedPreferences: SharedPreferences
    ) =
        SignInUseCase(signInRepository, sharedPreferences)


    @Provides
    @Singleton
    fun provideOtpTrustUseCase(
        otpTrustRepository: OtpTrustRepositoryInterface,
        sharedPreferences: SharedPreferences
    ) = OtpTrustUseCase(otpTrustRepository, sharedPreferences)


    @Provides
    @Singleton
    fun provideResetPasswordUseCase(resetPassword: ResetPasswordRepositoryInterface) =
        ResetPasswordUseCase(resetPassword)


    @Provides
    @Singleton
    fun provideNewRequestUseCase(newRequest: NewRequestRepositoryInterface) =
        NewRequestUseCase(newRequest)


}