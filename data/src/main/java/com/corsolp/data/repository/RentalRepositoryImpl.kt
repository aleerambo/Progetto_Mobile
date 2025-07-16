package com.corsolp.data.repository

import com.corsolp.data.remote.RentApi
import com.corsolp.data.remote.models.rent.GetAllRentalPosts
import com.corsolp.data.remote.models.rent.GetFavourites
import com.corsolp.data.remote.models.rent.GetRentalPostsByID
import com.corsolp.data.remote.models.rent.PostFavourites
import com.corsolp.domain.models.News
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.models.Service
import com.corsolp.domain.models.Neighborhood
import com.corsolp.domain.models.Favourite
import com.corsolp.domain.models.Rental
import com.corsolp.domain.models.RentalTypeEnum
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RentalRepositoryImpl(
    private val rentApi: RentApi
) : RentalRepository {

    // Usare un scope su IO per tutte le chiamate di rete
    private val scope = CoroutineScope(Dispatchers.IO)

    // 1) Flussi “live”
    private val _newsList             = MutableStateFlow<List<News>>(emptyList())
    override fun fetchNewsList(): StateFlow<List<News>> = _newsList

    private val _rentalTypeList       = MutableStateFlow<List<RentalType>>(emptyList())
    override fun fetchRentalTypeList(): StateFlow<List<RentalType>> = _rentalTypeList

    private val _serviceList          = MutableStateFlow<List<Service>>(emptyList())
    override fun fetchServiceList(): StateFlow<List<Service>> = _serviceList

    private val _neighborhoodList     = MutableStateFlow<List<Neighborhood>>(emptyList())
    override fun fetchNeighborhoodList(): StateFlow<List<Neighborhood>> = _neighborhoodList

    private val _allRentalPosts       = MutableStateFlow<List<Rental>>(emptyList())
    override fun fetchAllRentalPosts(): StateFlow<List<Rental>> = _allRentalPosts

    private val _favouritesList       = MutableStateFlow<List<Favourite>>(emptyList())
    override fun fetchFavouritesList(): StateFlow<List<Favourite>> = _favouritesList

    init {
        // Carico tutto al momento della creazione del repository
        scope.launch {
            // Carico News
            try {
                val newsApiList = rentApi.getNews() // List<GetNews>
                val newsDomain = newsApiList.map { api ->
                    News(
                        id = api.id ?: 0,
                        title = api.titolo ?: "",
                        content = api.contenuto ?: "",
                        publishDate = api.dataPubblicazione ?: "",
                        pictureUrl = api.fotoNews ?: "",
                    )
                }
                _newsList.value = newsDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Tipologie di Annuncio
            try {
                val typeApiList = rentApi.getRentalTypePosts() // List<getRentalTypePosts>
                val typeDomain = typeApiList.map { api ->
                    RentalType(
                        id = api.id ?: 0,
                        name = api.nome ?: "",
                    )
                }
                _rentalTypeList.value = typeDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Servizi
            try {
                val serviceApiList = rentApi.getServices() // List<GetServices>
                val serviceDomain = serviceApiList.map { api ->
                    Service(
                        id = api.id ?: 0,
                        name = api.nomeServizio ?: "",
                    )
                }
                _serviceList.value = serviceDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Quartieri
            try {
                val neighborhoodApiList = rentApi.getAreas() // List<GetAreas>
                val neighborhoodDomain = neighborhoodApiList.map { api ->
                    Neighborhood(
                        id = api.id ?: 0,
                        name = api.nomeQuartiere ?: "",
                        description = api.descrizione ?: ""
                    )
                }
                _neighborhoodList.value = neighborhoodDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Tutti gli Annunci
            try {
                val allApiList = rentApi.getAllRentalPosts() // List<GetAllRentalPosts>
                val allDomain = allApiList.map { api ->
                    api.toDomainFromAll(favorite = false)
                }
                _allRentalPosts.value = allDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Preferiti
            try {
                val favApiList = rentApi.getFavourites() // List<GetFavourites>
                val favDomain = favApiList.map { api ->
                    val rental = api.toDomainFromFav(favorite = true)
                    Favourite(
                        id = api.id ?: 0,
                        userId = api.utenteId ?: 0,
                        rental = rental
                    )
                }
                _favouritesList.value = favDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // -----------------------------------
    // Operazioni “one‐shot” (suspend)
    // -----------------------------------

    override suspend fun fetchNewsById(id: Int): Result<News> {
        return try {
            val api = rentApi.getNewsById(id) // GetNews
            val domain = News(
                id = api.id ?: 0,
                title = api.titolo ?: "",
                content = api.contenuto ?: "",
                publishDate = api.dataPubblicazione ?: "",
                pictureUrl = api.fotoNews ?: ""
            )
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchRentalPostById(id: Int): Result<Rental> {
        return try {
            val api = rentApi.getRentalPostsById(id) // GetRentalPostsByID
            // In questo caso, GetRentalPostsByID è identico a GetAllRentalPosts ma per un singolo elemento
            val domain = api.toDomainFromAll(false)
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createRentalPost(
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: List<Int>,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> = try {
        val response: Response<Unit> = rentApi.postRental(
            idArea            = idArea,
            price             = price,
            description       = description,
            rooms             = rooms,
            squareMeters      = squareMeters,
            floor             = floor,
            address           = address,
            selectedServices  = selectedServices.toTypedArray(),
            type              = type,
            numberOfTenants   = numberOfTenants,
            minContract       = minContract,
            maxContract       = maxContract
        )
        if (response.isSuccessful) {
                // Facoltativo: ricarico la lista completa degli annunci
                val updated = rentApi.getAllRentalPosts()
                    .map { it.toDomainFromAll(false) }
                _allRentalPosts.value = updated
                Result.success(Unit)
        } else {
                Result.failure(Exception("Errore creazione annuncio: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateRentalPost(
        id: Int,
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: List<Int>,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> {
        return try {
            val response: Response<Unit> = rentApi.updateRentalPost(
                id                = id,
                idArea            = idArea,
                price             = price,
                description       = description,
                rooms             = rooms,
                squareMeters      = squareMeters,
                floor             = floor,
                address           = address,
                selectedServices  = selectedServices.toTypedArray(),
                type              = type,
                numberOfTenants   = numberOfTenants,
                minContract       = minContract,
                maxContract       = maxContract
            )
            if (response.isSuccessful) {
                // Ricarico lista completa
                val updated = rentApi.getAllRentalPosts()
                    .map { it.toDomainFromAll(false) }
                _allRentalPosts.value = updated
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore aggiornamento annuncio: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun activateRentalPost(id: Int): Result<Unit> {
        return try {
            val response: Response<Unit> = rentApi.activateRentalPost(id)
            if (response.isSuccessful) {
                // Ricarico lista completa per riflettere il nuovo “stato”
                val updated = rentApi.getAllRentalPosts()
                    .map { it.toDomainFromAll(false) }
                _allRentalPosts.value = updated
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore attivazione annuncio: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteRentalPost(id: Int): Result<Unit> {
        return try {
            val response: Response<Unit> = rentApi.deleteRentalPost(id)
            if (response.isSuccessful) {
                // Ricarico lista completa rimuovendo l’annuncio eliminato
                val updated = rentApi.getAllRentalPosts()
                    .map { it.toDomainFromAll(false) }
                _allRentalPosts.value = updated
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore eliminazione annuncio: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addFavourite(rentalPostId: Int): Result<Unit> {
        return try {
            val postFavourite = PostFavourites(rentalPostId)
            val response: Response<Unit> = rentApi.postFavourites(postFavourite)
            if (response.isSuccessful) {
                // Ricarico la lista dei preferiti
                val favApiList = rentApi.getFavourites()
                _favouritesList.value = favApiList.map { api ->
                    // crea prima il Rental
                    val rental = api.toDomainFromFav(true)
                    // poi wrappa in Favourite, estraendo userId da api.utenteId
                    Favourite(
                        id     = api.id ?: 0,
                        userId = api.utenteId ?: 0,
                        rental = rental
                    )
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore aggiunta preferito: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteFavourite(id: Int): Result<Unit> {
        return try {
            val response: Response<Unit> = rentApi.deleteFavourite(id)
            if (response.isSuccessful) {
                // Ricarico la lista dei preferiti
                val favApiList = rentApi.getFavourites()
                _favouritesList.value = favApiList.map { api ->
                    // Prima ricavo il Rental
                    val rental = api.toDomainFromFav(true)
                    // Poi wrappo in Favourite
                    Favourite(
                        id     = api.id ?: 0,
                        userId = api.utenteId ?: 0,
                        rental = rental
                    )
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore rimozione preferito: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ----------------------------
    // Estensione privata per mappare
    // il risultato API in modello di dominio
    // ----------------------------

    /** Mappa GetAllRentalPosts o GetFavourites in Rental domain */
    private fun GetAllRentalPosts.toDomainFromAll(favorite: Boolean): Rental {
        val rentalTypeEnum = when(this.tipologia?.lowercase()) {
            "stanza"       -> RentalTypeEnum.ROOM
            "appartamento" -> RentalTypeEnum.APARTMENT
            "posto letto"  -> RentalTypeEnum.BED
            else           -> throw IllegalArgumentException("Tipo sconosciuto: ${this.tipologia}")
        }
        return Rental(
            id          = this.id ?: 0,
            description = this.descrizione.orEmpty().trim('"'),
            pictureUrl  = this.fotoAnnuncio,
            rooms       = this.locali ?: 0,
            surface     = this.mq ?: 0,
            floor       = this.piano ?: 0,
            services    = this.servizi?.split(',') ?: emptyList(),
            price       = this.prezzo?.toDoubleOrNull() ?: 0.0,
            favorite    = favorite,
            type        = rentalTypeEnum
        )
    }

    /** GetFavourites ha campi identici a GetAllRentalPosts, più utente_id */
    private fun GetFavourites.toDomainFromFav(favorite: Boolean): Rental {
        val rentalTypeEnum = when(this.tipologia?.lowercase()) {
            "stanza"       -> RentalTypeEnum.ROOM
            "appartamento" -> RentalTypeEnum.APARTMENT
            "posto letto"  -> RentalTypeEnum.BED
            else           -> throw IllegalArgumentException("Tipo sconosciuto: ${this.tipologia}")
        }
        return Rental(
            id          = this.id ?: 0,
            description = this.descrizione.orEmpty().trim('"'),
            pictureUrl  = this.fotoAnnuncio,
            rooms       = this.locali ?: 0,
            surface     = this.mq ?: 0,
            floor       = this.piano ?: 0,
            services    = emptyList(),
            price       = this.prezzo?.toDoubleOrNull() ?: 0.0,
            favorite    = favorite,
            type        = rentalTypeEnum
        )
    }

    private fun GetRentalPostsByID.toDomainFromAll(favorite: Boolean): Rental {
        return Rental(
            id          = this.id ?: 0,
            description = this.descrizione.orEmpty().trim('"'),
            pictureUrl  = this.fotoAnnuncio,
            rooms       = this.locali ?: 0,
            surface     = this.mq ?: 0,
            floor       = this.piano ?: 0,
            services    = this.servizi?.split(',') ?: emptyList(),
            price       = this.prezzo?.toDoubleOrNull() ?: 0.0,
            favorite    = favorite,
            type        = when(this.tipologia?.lowercase()) {
                "stanza"       -> RentalTypeEnum.ROOM
                "appartamento" -> RentalTypeEnum.APARTMENT
                "posto letto"  -> RentalTypeEnum.BED
                else           -> throw IllegalArgumentException("Tipo sconosciuto: ${this.tipologia}")
            }
        )
    }
}
