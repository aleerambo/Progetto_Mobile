package com.corsolp.domain.di

import com.corsolp.domain.usecases.ActivateRentalPostUseCase
import com.corsolp.domain.usecases.ActivateRentalPostUseCaseImpl
import com.corsolp.domain.usecases.AddFavouriteUseCase
import com.corsolp.domain.usecases.AddFavouriteUseCaseImpl
import com.corsolp.domain.usecases.CreateRentalPostUseCase
import com.corsolp.domain.usecases.CreateRentalPostUseCaseImpl
import com.corsolp.domain.usecases.DeleteFavouriteUseCase
import com.corsolp.domain.usecases.DeleteFavouriteUseCaseImpl
import com.corsolp.domain.usecases.DeleteRentalPostUseCase
import com.corsolp.domain.usecases.DeleteRentalPostUseCaseImpl
import com.corsolp.domain.usecases.FetchAllRentalPostsUseCase
import com.corsolp.domain.usecases.FetchAllRentalPostsUseCaseImpl
import com.corsolp.domain.usecases.FetchFavouritesUseCase
import com.corsolp.domain.usecases.FetchFavouritesUseCaseImpl
import com.corsolp.domain.usecases.FetchNeighborhoodListUseCase
import com.corsolp.domain.usecases.FetchNeighborhoodListUseCaseImpl
import com.corsolp.domain.usecases.FetchNewsUseCase
import com.corsolp.domain.usecases.FetchNewsUseCaseImpl
import com.corsolp.domain.usecases.FetchRentalPostByIdUseCase
import com.corsolp.domain.usecases.FetchRentalPostByIdUseCaseImpl
import com.corsolp.domain.usecases.FetchRentalTypeListUseCase
import com.corsolp.domain.usecases.FetchRentalTypeListUseCaseImpl
import com.corsolp.domain.usecases.FetchServiceListUseCase
import com.corsolp.domain.usecases.FetchServiceListUseCaseImpl
import com.corsolp.domain.usecases.FetchSingleNewsUseCase
import com.corsolp.domain.usecases.FetchSingleNewsUseCaseImpl
import com.corsolp.domain.usecases.GetProfileUseCase
import com.corsolp.domain.usecases.GetProfileUseCaseImpl
import com.corsolp.domain.usecases.LoginUseCase
import com.corsolp.domain.usecases.LoginUseCaseImpl
import com.corsolp.domain.usecases.LogoutUseCase
import com.corsolp.domain.usecases.LogoutUseCaseImpl
import com.corsolp.domain.usecases.RegisterUseCase
import com.corsolp.domain.usecases.RegisterUseCaseImpl
import com.corsolp.domain.usecases.UpdateRentalPostUseCase
import com.corsolp.domain.usecases.UpdateRentalPostUseCaseImpl

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
