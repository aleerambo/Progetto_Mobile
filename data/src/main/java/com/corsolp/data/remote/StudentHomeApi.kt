package com.corsolp.data.remote

import com.corsolp.data.remote.models.GetAllAnnunci
import com.corsolp.data.remote.models.GetAnnunciByID
import com.corsolp.data.remote.models.GetAnnunciFiltri
import com.corsolp.data.remote.models.GetAnnunciNoAttivi
import com.corsolp.data.remote.models.GetAuthProfile
import com.corsolp.data.remote.models.GetNews
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentHomeApi {
    @GET("/api/news")
    suspend fun getNews(): List<GetNews>

    @GET("/api/news/{id}")
    suspend fun getNewsById(
        @Path("id") id: Int
    ): GetNews

    @GET("/api/auth/profile")
    suspend fun getAuthProfile(): GetAuthProfile

    @GET("/api/annunci")
    suspend fun getAnnunci(): List<GetAllAnnunci>

    @GET("/api/lastannunci")
    suspend fun lastAnnunci(): List<GetAllAnnunci>

    @GET("/api/annunci/{id}")
    suspend fun getAnnuncioById(
        @Path("id") id: Int
    ): GetAnnunciByID

    @GET("/api/annuncinoattivi")
    suspend fun getAnnunciNoAttivi(): List<GetAnnunciNoAttivi>

    @GET("/api/annunci/prezzo/{prezzomax}")
    suspend fun getAnnunciPrezzo(
        @Path("prezzomax") prezzomax: Int
    ): List<GetAnnunciFiltri>

    @GET("/api/annunci/tipo/{tipo}/{prezzomax}")
    suspend fun getAnnunciTipoPrezzo(
        @Path("tipo") tipo: String,
        @Path("prezzomax") prezzomax: Int
    ): List<GetAnnunciFiltri>

    @GET("/api/annunci/quartiere/{id}/{prezzomax}")
    suspend fun getAnnunciQuartierePrezzo(
        @Path("id") id: Int,
        @Path("prezzomax") prezzomax: Int
    ): List<GetAnnunciFiltri>

    @GET("/api/annunci/filter/{tipo}/{quartiere}/{prezzomax}")
    suspend fun getAnnunciFilter(
        @Path("tipo") tipo: String,
        @Path("quartiere") quartiere: Int,
        @Path("prezzomax") prezzomax: Int
    ): List<GetAnnunciFiltri>

    @GET("/api/annunci/utente/{mail}")
    suspend fun getAnnunciByUtente(
        @Path("mail") mail: String
    ): List<GetAllAnnunci>

    @GET("/api/tipi-annuncio")

    @GET("/api/servizi")

    @GET("/api/quartieri")

    @GET("/api/preferiti")


    // /api/auth/register
    // /api/auth/login
    // /api/auth/logout
    // /api/preferiti
    // /api/annunci/create
    // /api/annunci/update/{id}
    // /api/annunci/modifica/{id}
    // /api/annunci/attiva/{id}

    // /api/preferiti/{annuncio_id}
    // /api/annunci/delete/{id}
}