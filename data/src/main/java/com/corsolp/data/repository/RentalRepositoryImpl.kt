// data/repository/RentalRepositoryImpl.kt
package com.corsolp.data.repository

import com.corsolp.data.remote.RentApi
import com.corsolp.data.remote.models.rent.GetAllRentalPosts
import com.corsolp.domain.models.Favourite
import com.corsolp.domain.models.Neighborhood
import com.corsolp.domain.models.News
import com.corsolp.domain.models.Rental
import com.corsolp.domain.models.RentalType
import com.corsolp.domain.models.RentalTypeEnum
import com.corsolp.domain.models.Service
import com.corsolp.domain.repository.RentalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RentalRepositoryImpl(
<<<<<<< HEAD
    private val rentApi: RentApi
) : RentalRepository {
=======
    private val studentHomeApi: StudentHomeApi
) : RentalRepository {
    private val scope = CoroutineScope(Dispatchers.Main)
>>>>>>> 4812a87f625f867d2f1ae227d1a319e3d64ec6ed

    // Usare un scope su IO per tutte le chiamate di rete
    private val scope = CoroutineScope(Dispatchers.IO)

<<<<<<< HEAD
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
                        publishDate = api.contenuto ?: "",
                        pictureUrl = api.fotoNews ?: ""
                    )
                }
                _newsList.value = newsDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Tipologie di Annuncio
            try {
                val typeApiList = rentApi.getRentalTypePosts() // List<GetRentalTypePosts>
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
                        description = api.descrizione ?: "",
                    )
                }
                _neighborhoodList.value = neighborhoodDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Tutti gli Annunci
            try {
                val allApiList = rentApi.getAllRentalPosts() // List<GetAllRentalPosts>
                val allDomain = allApiList.map { api: GetAllRentalPosts ->
                    api.toDomainRental(favorite = false)
                }
                _allRentalPosts.value = allDomain
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Carico Preferiti
            try {
                val favApiList = rentApi.getFavourites() // List<GetFavourites>
                val favDomain = favApiList.map { api ->
                    val rental = api.toDomainRental(favorite = true)
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
    // 2) Operazioni “one‐shot” (suspend)
    // -----------------------------------

    override suspend fun fetchNewsById(id: Int): Result<News> {
        return try {
            val api = rentApi.getNewsById(id) // GetNews
            val domain = News(id = api.id, title = api.title, content = api.content)
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun fetchRentalPostById(id: Int): Result<Rental> {
        return try {
            val api = rentApi.getRentalPostById(id) // GetRentalPostsByID
            // In questo caso, GetRentalPostsByID è identico a GetAllRentalPosts ma per un singolo elemento
            val domain = api.toDomainRental(favorite = false)
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
        type: String,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> {
        return try {
            val partMap = mapOf<String, Any>(
                "id_quartiere" to idArea,
                "prezzo" to price,
                "descrizione" to description,
                "locali" to rooms,
                "mq" to squareMeters,
                "piano" to floor,
                "indirizzo" to address,
                "selectedServizi" to selectedServices.toTypedArray(),
                "tipologia" to type, // es. "stanza", "appartamento", "posto letto"
                "numero_inquilini" to numberOfTenants,
                "contratto_min" to minContract,
                "contratto_max" to maxContract
            )
            val response: Response<Unit> = rentApi.postRental(partMap)
            if (response.isSuccessful) {
                // Facoltativo: ricarico la lista completa degli annunci
                val updated = rentApi.getAllRentalPosts().map { it.toDomainRental(favorite = false) }
                _allRentalPosts.value = updated
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore creazione annuncio: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
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
        type: String,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> {
        return try {
            val partMap = mapOf<String, Any>(
                "id_quartiere" to idArea,
                "prezzo" to price,
                "descrizione" to description,
                "locali" to rooms,
                "mq" to squareMeters,
                "piano" to floor,
                "indirizzo" to address,
                "selectedServizi" to selectedServices.toTypedArray(),
                "tipologia" to type,
                "numero_inquilini" to numberOfTenants,
                "contratto_min" to minContract,
                "contratto_max" to maxContract
            )
            val response: Response<Unit> = rentApi.updateRentalPost(id, partMap)
            if (response.isSuccessful) {
                // Ricarico lista completa
                val updated = rentApi.getAllRentalPosts().map { it.toDomainRental(favorite = false) }
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
                val updated = rentApi.getAllRentalPosts().map { it.toDomainRental(favorite = false) }
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
                val updated = rentApi.getAllRentalPosts().map { it.toDomainRental(favorite = false) }
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
                _favouritesList.value = favApiList.map { it.toDomainRental(favorite = true) }
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
                val favApiList = rentApi.getFavourites()
                _favouritesList.value = favApiList.map { it.toDomainRental(favorite = true) }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Errore rimozione preferito: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ----------------------------
    // 3) Estensione privata per mappare 
    //    il risultato API in modello di dominio
    // ----------------------------

    /** Mappa GetAllRentalPosts o GetFavourites in Rental domain */
    private fun GetAllRentalPosts.toDomainRental(favorite: Boolean): Rental {
        val rentalTypeEnum = when(this.tipologia.lowercase()) {
            "stanza"       -> RentalTypeEnum.ROOM
            "appartamento" -> RentalTypeEnum.APARTMENT
            "posto letto"  -> RentalTypeEnum.BED
            else           -> throw IllegalArgumentException("Tipo sconosciuto: ${this.tipologia}")
        }
        return Rental(
            id          = this.id,
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
    private fun GetFavourites.toDomainRental(favorite: Boolean): Rental {
        val rentalTypeEnum = when(this.tipologia.lowercase()) {
            "stanza"       -> RentalTypeEnum.ROOM
            "appartamento" -> RentalTypeEnum.APARTMENT
            "posto letto"  -> RentalTypeEnum.BED
            else           -> throw IllegalArgumentException("Tipo sconosciuto: ${this.tipologia}")
        }
        return Rental(
            id          = this.id,
            description = this.descrizione.orEmpty().trim('"'),
            pictureUrl  = this.foto_annuncio,
            rooms       = this.locali ?: 0,
            surface     = this.mq ?: 0,
            floor       = this.piano ?: 0,
            services    = this.servizi?.split(',') ?: emptyList(),
            price       = this.prezzo?.toDoubleOrNull() ?: 0.0,
            favorite    = favorite,
            type        = rentalTypeEnum
        )
    }
}
=======
    override fun fetchRentalTypeList() {
        scope.launch {
            val getLastRentalPosts = studentHomeApi.getLastRentalPosts()
            println("getLastRentalPosts: $getLastRentalPosts")

            val rentalTypes = getLastRentalPosts.map { data ->
                when (data.tipologia) {
                    "stanza" -> RentalType.Room(
                        roomDescription = data.descrizione ?: "",
                        roomPictureUrl = data.fotoAnnuncio ?: "",
                        roomRooms = data.locali ?: 0,
                        roomSurface = data.mq ?: 0,
                        roomFloor = data.piano ?: 0,
                        roomServices = data.servizi?.split(',') ?: listOf(),
                        roomPrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    "appartamento" -> RentalType.Apartment(
                        apartmentDescription = data.descrizione ?: "",
                        apartmentPictureUrl = data.fotoAnnuncio ?: "",
                        apartmentRooms = data.locali ?: 0,
                        apartmentSurface = data.mq ?: 0,
                        apartmentFloor = data.piano ?: 0,
                        apartmentServices = data.servizi?.split(',') ?: listOf(),
                        apartmentPrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    "posto letto" -> RentalType.Bed(
                        bedSpaceDescription = data.descrizione ?: "",
                        bedSpacePictureUrl = data.fotoAnnuncio ?: "",
                        bedSpaceRooms = data.locali ?: 0,
                        bedSpaceSurface = data.mq ?: 0,
                        bedSpaceFloor = data.piano ?: 0,
                        bedSpaceServices = data.servizi?.split(',') ?: listOf(),
                        bedSpacePrice = data.prezzo?.toDouble() ?: 0.0
                    )
                    else -> throw IllegalArgumentException("Unknown rental type: ${data.tipologia}")
                }
            }

            _rentalTypeList.emit(rentalTypes)
        }
    }
}
>>>>>>> 4812a87f625f867d2f1ae227d1a319e3d64ec6ed
