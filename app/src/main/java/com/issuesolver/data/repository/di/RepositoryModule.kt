package com.issuesolver.data.repository.di



import com.issuesolver.data.network.home.FilterService
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.home.RequestInterface
import com.issuesolver.data.repository.home.RequestRepositoryImpl
import com.issuesolver.data.repository.login.ConfirmOtpRepositoryImpl
import com.issuesolver.data.repository.login.ConfirmOtpRepositoryInterface
import com.issuesolver.data.repository.login.ForgetPasswordRepositoryImpl
import com.issuesolver.data.repository.login.ForgetPasswordRepositoryInterface
import com.issuesolver.data.repository.login.OtpTrustRepositoryImpl
import com.issuesolver.data.repository.login.OtpTrustRepositoryInterface
import com.issuesolver.data.repository.login.RegisterRepositoryImpl
import com.issuesolver.data.repository.login.RegisterRepositoryInterface
import com.issuesolver.data.repository.login.ResendOtpRepositoryImpl
import com.issuesolver.data.repository.login.ResendOtpRepositoryInterface
import com.issuesolver.data.repository.login.ResetPasswordRepositoryImpl
import com.issuesolver.data.repository.login.ResetPasswordRepositoryInterface
import com.issuesolver.data.repository.login.SignInRepositoryImpl
import com.issuesolver.data.repository.login.SignInRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.DeleteRequestByIdImpl
import com.issuesolver.data.repository.myrequestrepo.DeleteRequestByIdInterface
import com.issuesolver.data.repository.myrequestrepo.GetCommentRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.GetCommentRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.GetRequestByIdRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.GetRequestByIdRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.LikeRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.LikeRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.MyRequestRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.MyRequestRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.RemoveLikeRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.RemoveLikeRepositoryInterface
import com.issuesolver.data.repository.myrequestrepo.SendCommentRepositoryImpl
import com.issuesolver.data.repository.myrequestrepo.SendCommentRepositoryInterface
import com.issuesolver.data.repository.newrequestrepo.GetCategoryRepositoryImpl
import com.issuesolver.data.repository.newrequestrepo.GetCategoryRepositoryInterface
import com.issuesolver.data.repository.newrequestrepo.GetOrganizationRepositoryImpl
import com.issuesolver.data.repository.newrequestrepo.GetOrganizationRepositoryInterface
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryImpl
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryInterface
import com.issuesolver.data.repository.profile.DeleteAccountRepositoryImpl
import com.issuesolver.data.repository.profile.DeleteAccountRepositoryInterface
import com.issuesolver.data.repository.profile.GetMeRepositoryImpl
import com.issuesolver.data.repository.profile.GetMeRepositoryInterFace
import com.issuesolver.data.repository.profile.UpdateFullNameRepositoryImpl
import com.issuesolver.data.repository.profile.UpdateFullNameRepositoryInterFace
import com.issuesolver.data.repository.profile.UpdatePasswordRepositoryImpl
import com.issuesolver.data.repository.profile.UpdatePasswordRepositoryInterFace
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    @Binds
    @Singleton
    abstract fun provideGetCategoryRepository(repository: GetCategoryRepositoryImpl):GetCategoryRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideGetOrganizationRepository(repository: GetOrganizationRepositoryImpl):GetOrganizationRepositoryInterface



    @Binds
    @Singleton
    abstract fun provideRequestRepository(repository: RequestRepositoryImpl): RequestInterface

//    @Binds
//    @Singleton
//    abstract fun provideFilterRepository(repository: FilterRepositoryImpl): FilterInterface

    @Binds
    @Singleton
    abstract fun provideMyRequestRepository(repository: MyRequestRepositoryImpl): MyRequestRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideGetRequestByIdRepository(repository: GetRequestByIdRepositoryImpl): GetRequestByIdRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideLikeRepository(repository: LikeRepositoryImpl): LikeRepositoryInterface


    @Binds
    @Singleton
    abstract fun provideRemoveLikeRepository(repository: RemoveLikeRepositoryImpl): RemoveLikeRepositoryInterface

    @Binds
    @Singleton
    abstract fun provideSendCommentRepository(repository: SendCommentRepositoryImpl): SendCommentRepositoryInterface


    @Binds
    @Singleton
    abstract fun provideGetCommentRepository(repository: GetCommentRepositoryImpl): GetCommentRepositoryInterface


    @Binds
    @Singleton
    abstract fun provideDeleteRequestByIdRepository(repository: DeleteRequestByIdImpl): DeleteRequestByIdInterface



}