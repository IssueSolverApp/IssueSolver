package com.issuesolver.domain.entity.networkModel.login

data class RefreshTokenResponse(
    var data: RefreshData? = RefreshData(),
    var success: Boolean? = null,
    var message: String? = null
)

