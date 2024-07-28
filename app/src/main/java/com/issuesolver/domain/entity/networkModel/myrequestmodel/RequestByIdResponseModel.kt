package com.issuesolver.domain.entity.networkModel.myrequestmodel

import com.issuesolver.domain.entity.networkModel.home.FilterData

data class RequestByIdResponseModel(
    val data: FilterData = FilterData(),
    val message: String? = null,
    val fullName: String? = null,
    val success: Boolean? = null
)
