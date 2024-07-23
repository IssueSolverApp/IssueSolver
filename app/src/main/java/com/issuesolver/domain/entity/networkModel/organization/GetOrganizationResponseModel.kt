package com.issuesolver.domain.entity.networkModel.organization


data class GetOrganizationResponseModel(
    val data: List<OrganizationData>,
    val message: String,
    val success: Boolean
)