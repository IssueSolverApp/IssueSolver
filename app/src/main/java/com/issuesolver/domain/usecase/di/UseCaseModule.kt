package com.issuesolver.domain.usecase.di

import android.content.SharedPreferences
import com.issuesolver.data.repository.login.ConfirmOtpRepositoryInterface
import com.issuesolver.data.repository.login.OtpTrustRepositoryInterface
import com.issuesolver.data.repository.profile.DeleteAccountRepositoryInterface
import com.issuesolver.data.repository.profile.GetMeRepositoryInterFace
import com.issuesolver.data.repository.login.RegisterRepositoryInterface
import com.issuesolver.data.repository.login.ResendOtpRepositoryInterface
import com.issuesolver.data.repository.login.ResetPasswordRepositoryInterface
import com.issuesolver.data.repository.login.SignInRepositoryInterface
import com.issuesolver.data.repository.profile.UpdateFullNameRepositoryInterFace
import com.issuesolver.data.repository.profile.UpdatePasswordRepositoryInterFace
import com.issuesolver.domain.usecase.login.backend.OtpTrustUseCase
import com.issuesolver.domain.usecase.login.backend.RegisterUseCase
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryInterface
import com.issuesolver.domain.usecase.login.backend.ConfirmOtpUseCase
import com.issuesolver.domain.usecase.login.backend.ResendOtpUseCase
import com.issuesolver.domain.usecase.login.backend.ResetPasswordUseCase
import com.issuesolver.domain.usecase.login.backend.SignInUseCase
import com.issuesolver.domain.usecase.login.local.ValidateFullNameUseCase
import com.issuesolver.domain.usecase.login.local.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.local.LoginUseCase
import com.issuesolver.domain.usecase.login.local.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.local.ValidateNewPasswordUseCase
import com.issuesolver.domain.usecase.login.local.ValidateRepeatedPasswordUseCase
import com.issuesolver.domain.usecase.profile.backend.DeleteAccountUseCase
import com.issuesolver.domain.usecase.profile.backend.GetMeUseCase
import com.issuesolver.domain.usecase.profile.backend.UpdateFullNameUseCase
import com.issuesolver.domain.usecase.profile.backend.UpdatePasswordUseCase
import com.issuesolver.domain.usecase.profile.local.ConfirmNewPasswordUseCase
import com.issuesolver.domain.usecase.profile.local.FullNameUseCase
import com.issuesolver.domain.usecase.profile.local.NewPasswordUseCase
import com.issuesolver.domain.usecase.profile.local.PreviousPasswordUseCase
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

            //

    @Provides
    @Singleton
    fun provideConfirmNewPasswordUseCase() = ConfirmNewPasswordUseCase()

    @Provides
    @Singleton
    fun provideFullNameUseCase() = FullNameUseCase()

    @Provides
    @Singleton
    fun provideNewPasswordUseCase() = NewPasswordUseCase()

    @Provides
    @Singleton
    fun providePreviousPasswordUseCase() = PreviousPasswordUseCase()



    @Provides
    @Singleton
    fun provideDeleteAccountUseCase(deleteAccountRepository: DeleteAccountRepositoryInterface) =
        DeleteAccountUseCase(deleteAccountRepository)

    @Provides
    @Singleton
    fun provideGetMeUseCase(getMeRepository: GetMeRepositoryInterFace) =
        GetMeUseCase(getMeRepository)

    @Provides
    @Singleton
    fun provideUpdateFullNameUseCase(updateFullNameRepository: UpdateFullNameRepositoryInterFace) =
        UpdateFullNameUseCase(updateFullNameRepository)

    @Provides
    @Singleton
    fun provideUpdatePasswordUseCase(updatePasswordRepository: UpdatePasswordRepositoryInterFace) =
        UpdatePasswordUseCase(updatePasswordRepository)



    @Provides
    @Singleton
    fun provideNewRequestUseCase(newRequest: NewRequestRepositoryInterface) =
        NewRequestUseCase(newRequest)


}