package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class PostRegister (

  @Json(name = "nome"     ) var nome     : String? = null,
  @Json(name = "cognome"  ) var cognome  : String? = null,
  @Json(name = "telefono" ) var telefono : String? = null,
  @Json(name = "mail"     ) var mail     : String? = null,
  @Json(name = "password" ) var password : String? = null

)