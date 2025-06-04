package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAreas (

  @Json(name = "id"             ) var id            : Int?    = null,
  @Json(name = "nome_quartiere" ) var nomeQuartiere : String? = null,
  @Json(name = "descrizione"    ) var descrizione   : String? = null

)