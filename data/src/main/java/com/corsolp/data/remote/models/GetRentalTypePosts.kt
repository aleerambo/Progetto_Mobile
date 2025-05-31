package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class GetRentalTypePosts (

  @Json(name = "id"   ) var id   : Int?    = null,
  @Json(name = "nome" ) var nome : String? = null

)