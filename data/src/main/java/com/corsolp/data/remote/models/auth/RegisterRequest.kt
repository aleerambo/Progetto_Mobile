package com.corsolp.data.remote.models.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest (

    @Json(name = "nome"     ) var nome     : String? = null,
    @Json(name = "cognome"  ) var cognome  : String? = null,
    @Json(name = "telefono" ) var telefono : String? = null,
    @Json(name = "mail"     ) var mail     : String? = null,
    @Json(name = "password" ) var password : String? = null

)