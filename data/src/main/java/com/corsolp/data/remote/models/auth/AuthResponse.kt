package com.corsolp.data.remote.models.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse (

  @Json(name = "message" ) var message : String? = null,
  @Json(name = "token"   ) var token   : String? = null,
  @Json(name = "user"    ) var user    : User?   = User()

)