package com.issuesolver.domain.useCase

import com.issuesolver.data.repository.RegisterRepositoryInterface
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepositoryInterface) {
    suspend operator fun invoke(user: RegisterRequestModel): Result<Any> {
        return try {
            val response = registerRepository.createUser(user)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

//TODO("Надо будет перевести его в основной di домаин уровня")
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUseCase(registerRepository: RegisterRepositoryInterface) =
        RegisterUseCase(registerRepository)


}