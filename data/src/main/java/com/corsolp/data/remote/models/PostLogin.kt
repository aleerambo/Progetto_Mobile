package com.corsolp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostLogin (

  @Json(name = "mail"     ) var mail     : String? = null,
  @Json(name = "password" ) var password : String? = null

)