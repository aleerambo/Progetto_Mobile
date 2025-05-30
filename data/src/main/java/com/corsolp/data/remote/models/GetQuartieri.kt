package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class GetQuartieri (

  @Json(name = "id"             ) var id            : Int?    = null,
  @Json(name = "nome_quartiere" ) var nomeQuartiere : String? = null,
  @Json(name = "descrizione"    ) var descrizione   : String? = null

)