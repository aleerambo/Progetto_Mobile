package com.corsolp.domain.di

import com.corsolp.domain.repository.AuthRepository
import com.corsolp.domain.repository.RentalRepository
import com.corsolp.domain.usecases.*

// Singleton -> rimane in memoria per tutta la vita dell'applicazione
object UseCaseProvider {

    // Auth‐usecase
    lateinit var loginUseCase: LoginUseCase
    lateinit var registerUseCase: RegisterUseCase
    lateinit var logoutUseCase: LogoutUseCase
    lateinit var getProfileUseCase: GetProfileUseCase

    // News / Rental / Favourites / Auxiliary‐usecase
    lateinit var fetchNewsUseCase: FetchNewsUseCase
    lateinit var fetchSingleNewsUseCase: FetchSingleNewsUseCase

    lateinit var fetchRentalTypeListUseCase: FetchRentalTypeListUseCase
    lateinit var fetchServiceListUseCase: FetchServiceListUseCase
    lateinit var fetchNeighborhoodListUseCase: FetchNeighborhoodListUseCase

    lateinit var fetchAllRentalPostsUseCase: FetchAllRentalPostsUseCase
    lateinit var fetchRentalPostByIdUseCase: FetchRentalPostByIdUseCase
    lateinit var createRentalPostUseCase: CreateRentalPostUseCase
    lateinit var updateRentalPostUseCase: UpdateRentalPostUseCase
    lateinit var activateRentalPostUseCase: ActivateRentalPostUseCase
    lateinit var deleteRentalPostUseCase: DeleteRentalPostUseCase

    lateinit var fetchFavouritesUseCase: FetchFavouritesUseCase
    lateinit var addFavouriteUseCase: AddFavouriteUseCase
    lateinit var deleteFavouriteUseCase: DeleteFavouriteUseCase

    // Il metodo setup viene chiamato all’avvio (tipicamente da RepositoryProvider)
    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        // 1) Auth
        loginUseCase = LoginUseCaseImpl(authRepository = repositoryProvider.authRepository)
        registerUseCase = RegisterUseCaseImpl(authRepository = repositoryProvider.authRepository)
        logoutUseCase = LogoutUseCaseImpl(authRepository = repositoryProvider.authRepository)
        getProfileUseCase = GetProfileUseCaseImpl(authRepository = repositoryProvider.authRepository)

        // 2) News (lista + dettaglio)
        fetchNewsUseCase = FetchNewsUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        fetchSingleNewsUseCase = FetchSingleNewsUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)

        // 3) Auxiliary (tipi, servizi, quartieri)
        fetchRentalTypeListUseCase = FetchRentalTypeListUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        fetchServiceListUseCase = FetchServiceListUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        fetchNeighborhoodListUseCase = FetchNeighborhoodListUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)

        // 4) Rental posts (lista + dettaglio + CRUD)
        fetchAllRentalPostsUseCase = FetchAllRentalPostsUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        fetchRentalPostByIdUseCase = FetchRentalPostByIdUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        createRentalPostUseCase = CreateRentalPostUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        updateRentalPostUseCase = UpdateRentalPostUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        activateRentalPostUseCase = ActivateRentalPostUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        deleteRentalPostUseCase = DeleteRentalPostUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)

        // 5) Favourites
        fetchFavouritesUseCase = FetchFavouritesUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        addFavouriteUseCase = AddFavouriteUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
        deleteFavouriteUseCase = DeleteFavouriteUseCaseImpl(rentalRepository = repositoryProvider.rentalRepository)
    }
}
