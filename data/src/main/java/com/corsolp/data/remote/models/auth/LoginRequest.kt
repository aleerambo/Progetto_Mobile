package com.corsolp.data.remote.models.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest (

    @Json(name = "mail"     ) var mail     : String? = null,
    @Json(name = "password" ) var password : String? = null

)