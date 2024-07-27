package com.issuesolver.data.network.myrequest

import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.home.LikeResponse
import com.issuesolver.domain.entity.networkModel.login.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import com.issuesolver.domain.entity.networkModel.organization.GetOrganizationResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyRequestService {

    @GET("request/user-requests")
    suspend fun myRequest(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<FilterResponseModel>


    @GET("request/by-id/{requestId}")
    suspend fun requestById(@Path("requestId") requestId: Int): Response<FilterResponseModel>


    @POST("api/v1/likes/post")
    suspend fun like(@Query("requestId") requestId: Int?): Response<LikeResponse>
    //api/v1/likes/post
    //requestId

    @DELETE("api/v1/likes")
    suspend fun removeLike(@Query("requestId") requestId: Int?): Response<LikeResponse>


    @POST("api/v1/comments/post")
    suspend fun sendComment(
        @Query("requestId") requestId: Int,
        @Body commentText: CommentRequest
    ): Response<CommentResponse>


    @GET("api/v1/comments")
    suspend fun getComments(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<CommentResponse>


    @GET("request/by-id/{requestId}")
    suspend fun deleteRequestById(@Path("requestId") requestId: Int): Response<FilterResponseModel>



}