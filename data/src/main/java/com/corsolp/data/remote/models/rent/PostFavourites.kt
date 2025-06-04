package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostFavourites (

  @Json(name = "annuncio_id" ) var annuncioId : Int? = null

)