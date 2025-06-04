package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetNews (

  @Json(name = "id"                 ) var id                : Int?    = null,
  @Json(name = "titolo"             ) var titolo            : String? = null,
  @Json(name = "contenuto"          ) var contenuto         : String? = null,
  @Json(name = "data_pubblicazione" ) var dataPubblicazione : String? = null,
  @Json(name = "foto_news"          ) var fotoNews          : String? = null

)