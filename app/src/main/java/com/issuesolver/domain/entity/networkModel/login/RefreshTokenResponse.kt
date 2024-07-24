package com.issuesolver.domain.entity.networkModel.login

data class RefreshTokenResponse(
    var data: RefreshData? = RefreshData(),
    var success: Boolean? = null,
    var message: String? = null
)

//
//{
//    "data": {
//    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpdGllcyI6WyJVU0VSIiwiRU1QVFkiXSwic3ViIjoiYW1pbmFtZWhkaTYyQGdtYWlsLmNvbSIsImZ1bGxOYW1lIjoiQW1pbmEiLCJuYmYiOjE3MjE4MDc4NzUsImV4cCI6MTcyMTgwODE3NSwiaXNzIjoiaWxraW5AU3VsZXltYW5vdi5jb20iLCJhdWQiOlsiaWxraW5AU3VsZXltYW5vdi5jb20iLCJpbGtpbkBTdWxleW1hbm92LmNvbSJdfQ.H9FIP_7xVrujxGYTIovWmYp66eQo3Eo5sC18QOW1ulE"
//},
//    "message": "Successfully Operation",
//    "success": true
//}