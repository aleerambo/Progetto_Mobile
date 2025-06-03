package com.corsolp.data.remote.models.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User (

    @Json(name = "id"    ) var id    : Int?    = null,
    @Json(name = "mail"  ) var mail  : String? = null,
    @Json(name = "ruolo" ) var ruolo : String? = null

)