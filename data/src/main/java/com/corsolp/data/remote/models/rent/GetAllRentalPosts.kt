package com.corsolp.data.remote.models.rent

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAllRentalPosts (

  @Json(name = "id"                         ) var id                       : Int?    = null,
  @Json(name = "cognome"                    ) var cognome                  : String? = null,
  @Json(name = "nome"                       ) var nome                     : String? = null,
  @Json(name = "mail"                       ) var mail                     : String? = null,
  @Json(name = "telefono"                   ) var telefono                 : String? = null,
  @Json(name = "ruolo"                      ) var ruolo                    : String? = null,
  @Json(name = "foto_profilo"               ) var fotoProfilo              : String? = null,
  @Json(name = "data"                       ) var data                     : String? = null,
  @Json(name = "prezzo"                     ) var prezzo                   : String? = null,
  @Json(name = "descrizione"                ) var descrizione              : String? = null,
  @Json(name = "locali"                     ) var locali                   : Int?    = null,
  @Json(name = "mq"                         ) var mq                       : Int?    = null,
  @Json(name = "piano"                      ) var piano                    : Int?    = null,
  @Json(name = "indirizzo"                  ) var indirizzo                : String? = null,
  @Json(name = "foto_annuncio"              ) var fotoAnnuncio             : String? = null,
  @Json(name = "quartiere_zona_descrizione" ) var quartiereZonaDescrizione : String? = null,
  @Json(name = "servizi"                    ) var servizi                  : String? = null,
  @Json(name = "contratto_max"              ) var contrattoMax             : Int?    = null,
  @Json(name = "contratto_min"              ) var contrattoMin             : Int?    = null,
  @Json(name = "numero_inquilini"           ) var numeroInquilini          : Int?    = null,
  @Json(name = "tipologia"                  ) var tipologia                : String? = null

)