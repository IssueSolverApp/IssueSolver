package com.issuesolver.data.repository

import com.issuesolver.data.network.profile.ProfileService
import com.issuesolver.domain.entity.networkModel.DeleteAccountRequest
import com.issuesolver.domain.entity.networkModel.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

interface DeleteAccountRepositoryInterface{

        suspend fun deleteAccount(profile: DeleteAccountRequest): retrofit2.Response<ProfileResponse>

}
class DeleteAccountRepositoryImpl @Inject constructor(private val profileService: ProfileService): DeleteAccountRepositoryInterface{
    override suspend fun deleteAccount(deleteAccount: DeleteAccountRequest): Response<ProfileResponse> {

        return profileService.delete(deleteAccount)

    }

}