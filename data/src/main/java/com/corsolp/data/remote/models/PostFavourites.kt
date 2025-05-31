package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class PostFavourites (

  @Json(name = "annuncio_id" ) var annuncioId : Int? = null

)