package com.issuesolver.data.repository.di

import com.issuesolver.data.repository.ConfirmOtpRepositoryImpl
import com.issuesolver.data.repository.ConfirmOtpRepositoryInterface
import com.issuesolver.data.repository.ForgetPasswordRepositoryImpl
import com.issuesolver.data.repository.ForgetPasswordRepositoryInterface
import com.issuesolver.data.repository.OtpTrustRepositoryImpl
import com.issuesolver.data.repository.OtpTrustRepositoryInterface
import com.issuesolver.data.repository.RegisterRepositoryImpl
import com.issuesolver.data.repository.RegisterRepositoryInterface
import com.issuesolver.data.repository.ResendOtpRepositoryImpl
import com.issuesolver.data.repository.ResendOtpRepositoryInterface
import com.issuesolver.data.repository.ResetPasswordRepositoryImpl
import com.issuesolver.data.repository.ResetPasswordRepositoryInterface
import com.issuesolver.data.repository.SignInRepositoryImpl
import com.issuesolver.data.repository.SignInRepositoryInterface
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryImpl
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryInterface
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
    abstract fun provideSignInRepository(repository: SignInRepositoryImpl): SignInRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideResendOtpRepository(repository: ResendOtpRepositoryImpl): ResendOtpRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideConfirmOtpRepository(repository: ConfirmOtpRepositoryImpl): ConfirmOtpRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideForgetPasswordRepository(repository: ForgetPasswordRepositoryImpl): ForgetPasswordRepositoryInterface


    @Binds
    @Singleton
    abstract fun provideOtpTrustRepository(repository: OtpTrustRepositoryImpl): OtpTrustRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideResetPasswordRepository(repository: ResetPasswordRepositoryImpl): ResetPasswordRepositoryInterface
    //
    @Binds
    @Singleton
    abstract fun provideGetMeRepository(repository: GetMeRepositoryImpl): GetMeRepositoryInterFace
    @Binds
    @Singleton
    abstract fun provideUpdatePasswordRepository(repository: UpdatePasswordRepositoryImpl): UpdatePasswordRepositoryInterFace
    @Binds
    @Singleton
    abstract fun provideUpdateFullNameRepository(repository: UpdateFullNameRepositoryImpl): UpdateFullNameRepositoryInterFace
    @Binds
    @Singleton
    abstract fun provideDeleteAccountRepository(repository: DeleteAccountRepositoryImpl): DeleteAccountRepositoryInterface


    @Binds
    @Singleton
    abstract fun provideNewRequestRepository(repository: NewRequestRepositoryImpl): NewRequestRepositoryInterface



}