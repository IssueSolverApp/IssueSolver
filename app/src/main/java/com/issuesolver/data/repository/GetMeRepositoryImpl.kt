package com.issuesolver.data.repository

import com.issuesolver.data.network.profile.ProfileService
import com.issuesolver.domain.entity.networkModel.GetMeResponse
import retrofit2.Response
import javax.inject.Inject

interface GetMeRepositoryInterFace{
    suspend fun getMe(): Response<GetMeResponse>
}
class GetMeRepositoryImpl @Inject constructor(private val profileService: ProfileService): GetMeRepositoryInterFace{
    override suspend fun getMe(): Response<GetMeResponse> {
        return profileService.getme()
    }
}