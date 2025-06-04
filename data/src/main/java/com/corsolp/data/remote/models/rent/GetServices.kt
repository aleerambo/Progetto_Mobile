package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetServices (

  @Json(name = "id"            ) var id           : Int?    = null,
  @Json(name = "nome_servizio" ) var nomeServizio : String? = null

)