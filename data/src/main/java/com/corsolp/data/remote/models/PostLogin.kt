package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class PostLogin (

  @Json(name = "mail"     ) var mail     : String? = null,
  @Json(name = "password" ) var password : String? = null

)