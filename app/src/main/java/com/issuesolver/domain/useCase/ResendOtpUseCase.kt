package com.issuesolver.domain.useCase

import com.issuesolver.data.repository.ResendOtpRepositoryImpl
import com.issuesolver.data.repository.ResendOtpRepositoryInterface
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import javax.inject.Inject

class ResendOtpUseCase @Inject constructor(private val otpRepository: ResendOtpRepositoryInterface) {

    suspend operator fun invoke(otpModel:ResendOtpModel):Result<Any>{

        return try {
            val response = otpRepository.resendOtp(otpModel)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create user"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}