package com.issuesolver.data.repository.profile

import com.issuesolver.data.network.profile.ProfileService
import com.issuesolver.domain.entity.networkModel.profile.ProfileResponse
import com.issuesolver.domain.entity.networkModel.profile.UpdatePasswordRequest
import retrofit2.Response
import javax.inject.Inject

interface UpdatePasswordRepositoryInterFace{
    suspend fun updatePassword(profile: UpdatePasswordRequest): Response<ProfileResponse>
}
class UpdatePasswordRepositoryImpl @Inject constructor(private val profileService: ProfileService):
    UpdatePasswordRepositoryInterFace {
    override suspend fun updatePassword(updatepassword: UpdatePasswordRequest): Response<ProfileResponse> {
        return profileService.updatepassword(updatepassword)
    }
}