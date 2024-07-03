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
import com.issuesolver.domain.useCase.ConfirmOtpUseCase
import com.issuesolver.domain.useCase.OtpTrustUseCase
import com.issuesolver.domain.useCase.RegisterUseCase

import com.issuesolver.domain.useCase.ResendOtpUseCase
import com.issuesolver.domain.useCase.ResetPasswordUseCase
import com.issuesolver.domain.useCase.SignInUseCase
import com.issuesolver.domain.usecase.login.ValidateFullNameUseCase
import com.issuesolver.domain.usecase.login.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.LoginUseCase
import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidateNewPasswordUseCase

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
    fun provideLoginUseCase()= LoginUseCase()

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
    fun provideSignInUseCase(signInRepository: SignInRepositoryInterface) =
        SignInUseCase(signInRepository)


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
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }


}