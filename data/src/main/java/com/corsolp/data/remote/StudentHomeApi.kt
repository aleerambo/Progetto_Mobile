package com.corsolp.data.remote

import com.corsolp.data.remote.models.GetAllRentalPosts
import com.corsolp.data.remote.models.GetRentalPostsByID
import com.corsolp.data.remote.models.GetRentalPostsFilter
import com.corsolp.data.remote.models.GetRentalPostsInactive
import com.corsolp.data.remote.models.GetAuthProfile
import com.corsolp.data.remote.models.GetNews
import com.corsolp.data.remote.models.GetFavourites
import com.corsolp.data.remote.models.GetAreas
import com.corsolp.data.remote.models.GetServices
import com.corsolp.data.remote.models.GetRentalTypePosts
import com.corsolp.data.remote.models.PostLogin
import com.corsolp.data.remote.models.PostFavourites
import com.corsolp.data.remote.models.PostRegister
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    suspend fun getAllRentalPosts(): List<GetAllRentalPosts>

    @GET("/api/lastannunci")
    suspend fun lastRentalPosts(): List<GetAllRentalPosts>

    @GET("/api/annunci/{id}")
    suspend fun getRentalPostsById(
        @Path("id") id: Int
    ): GetRentalPostsByID

    @GET("/api/annuncinoattivi")
    suspend fun getRentalPostsInactive(): List<GetRentalPostsInactive>

    @GET("/api/annunci/prezzo/{prezzomax}")
    suspend fun getRentalPostsByPrice(
        @Path("prezzomax") prezzomax: Int
    ): List<GetRentalPostsFilter>

    @GET("/api/annunci/tipo/{tipo}/{prezzomax}")
    suspend fun getRentalPostsByTypeAndPrice(
        @Path("tipo") tipo: String,
        @Path("prezzomax") prezzomax: Int
    ): List<GetRentalPostsFilter>

    @GET("/api/annunci/quartiere/{id}/{prezzomax}")
    suspend fun getRentalPostsByAreaAndPrice(
        @Path("id") id: Int,
        @Path("prezzomax") prezzomax: Int
    ): List<GetRentalPostsFilter>

    @GET("/api/annunci/filter/{tipo}/{quartiere}/{prezzomax}")
    suspend fun getRentalPostsFilter(
        @Path("tipo") tipo: String,
        @Path("quartiere") quartiere: Int,
        @Path("prezzomax") prezzomax: Int
    ): List<GetRentalPostsFilter>

    @GET("/api/annunci/utente/{mail}")
    suspend fun getRentalPostsByUser(
        @Path("mail") mail: String
    ): List<GetAllRentalPosts>

    @GET("/api/tipi-annuncio")
    suspend fun getRentalTypePosts(): List<GetRentalTypePosts>

    @GET("/api/servizi")
    suspend fun getServices(): List<GetServices>

    @GET("/api/quartieri")
    suspend fun getAreas(): List<GetAreas>

    @GET("/api/preferiti")
    suspend fun getFavourites(): List<GetFavourites>


    @Headers("Content-Type: application/json")
    @POST("/api/auth/register")
    suspend fun postRegister(
        @Body user: PostRegister
    ): Int

    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun postLogin(
        @Body user: PostLogin
    ): Int

    @POST("/api/auth/logout")
    suspend fun postLogout(): Int

    @Headers("Content-Type: application/json")
    @POST("/api/preferiti")
    suspend fun postFavourites(
        @Body favourites: PostFavourites
    ): Int

    @Multipart
    @POST("/api/annunci/create")
    suspend fun postRental(
        @Part("id_quartiere") idArea: Int,
        @Part("prezzo") price: Double,
        @Part("descrizione") description: String,
        @Part("locali") rooms: Int,
        @Part("mq") squareMeters: Int,
        @Part("piano") floor: Int,
        @Part("indirizzo") address: String,
        @Part("selectedServizi") selectedServices: Array<Int>,
        @Part("tipologia") type: Int,
        @Part("numero_inquilini") numberOfTenants: Int,
        @Part("contratto_min") minContract: Int,
        @Part("contratto_max") maxContract: Int,
    ): Int

    @Multipart
    @POST("/api/annunci/update/{id}")
    suspend fun updateRentalPost(
        @Path("id") id: Int,
        @Part("id_quartiere") idArea: Int,
        @Part("prezzo") price: Double,
        @Part("descrizione") description: String,
        @Part("locali") rooms: Int,
        @Part("mq") squareMeters: Int,
        @Part("piano") floor: Int,
        @Part("indirizzo") address: String,
        @Part("selectedServizi") selectedServices: Array<Int>,
        @Part("tipologia") type: Int,
        @Part("numero_inquilini") numberOfTenants: Int,
        @Part("contratto_min") minContract: Int,
        @Part("contratto_max") maxContract: Int,
    ): Int

    @POST("/api/annunci/attiva/{id}")
    suspend fun activateRentalPost(
        @Path("id") id: Int
    ): Int

    @DELETE("/api/preferiti/{id}")
    suspend fun deleteFavourite(
        @Path("id") id: Int
    ): Int

    @DELETE("/api/annunci/delete/{id}")
    suspend fun deleteRentalPost(
        @Path("id") id: Int
    ): Int
}