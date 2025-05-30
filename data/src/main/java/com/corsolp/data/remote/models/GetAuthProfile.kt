package com.corsolp.data.remote.models

import com.squareup.moshi.Json

data class GetAuthProfile (

  @Json(name = "id"    ) var id    : Int?    = null,
  @Json(name = "mail"  ) var mail  : String? = null,
  @Json(name = "ruolo" ) var ruolo : String? = null,
  @Json(name = "iat"   ) var iat   : Int?    = null,
  @Json(name = "exp"   ) var exp   : Int?    = null

)