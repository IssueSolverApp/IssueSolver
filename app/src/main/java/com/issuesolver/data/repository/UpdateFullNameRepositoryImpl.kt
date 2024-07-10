package com.issuesolver.data.repository

import com.issuesolver.data.network.profile.ProfileService
import com.issuesolver.domain.entity.networkModel.ProfileResponse
import com.issuesolver.domain.entity.networkModel.UpdateFullNameRequest
import javax.inject.Inject

interface UpdateFullNameRepositoryInterFace{
    suspend fun updateFullName(profile: UpdateFullNameRequest): retrofit2.Response<ProfileResponse>
}

class UpdateFullNameRepositoryImpl @Inject constructor(private val profileService: ProfileService):UpdateFullNameRepositoryInterFace{
    override suspend fun updateFullName(updatefullname: UpdateFullNameRequest): retrofit2.Response<ProfileResponse> {
        return profileService.updatefullname(updatefullname)
    }


}