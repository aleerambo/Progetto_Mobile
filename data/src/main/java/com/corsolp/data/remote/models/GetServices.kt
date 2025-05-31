package com.corsolp.data.remote.models

import com.squareup.moshi.Json


data class GetServices (

  @Json(name = "id"            ) var id           : Int?    = null,
  @Json(name = "nome_servizio" ) var nomeServizio : String? = null

)