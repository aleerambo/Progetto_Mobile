package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetFavourites (

  @Json(name = "id"            ) var id           : Int?    = null,
  @Json(name = "utente_id"     ) var utenteId     : Int?    = null,
  @Json(name = "id_quartiere"  ) var idQuartiere  : Int?    = null,
  @Json(name = "data"          ) var data         : String? = null,
  @Json(name = "prezzo"        ) var prezzo       : String? = null,
  @Json(name = "descrizione"   ) var descrizione  : String? = null,
  @Json(name = "locali"        ) var locali       : Int?    = null,
  @Json(name = "mq"            ) var mq           : Int?    = null,
  @Json(name = "piano"         ) var piano        : Int?    = null,
  @Json(name = "indirizzo"     ) var indirizzo    : String? = null,
  @Json(name = "stato"         ) var stato        : String? = null,
  @Json(name = "foto_annuncio" ) var fotoAnnuncio : String? = null,
  @Json(name = "thumbnails"    ) var thumbnails   : String? = null,
  @Json(name = "tipologia"     ) var tipologia    : String? = null,
)