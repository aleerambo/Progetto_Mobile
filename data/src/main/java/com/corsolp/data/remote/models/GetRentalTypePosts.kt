package com.corsolp.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetRentalTypePosts (

  @Json(name = "id"   ) var id   : Int?    = null,
  @Json(name = "nome" ) var nome : String? = null

)