package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(
 *    idArea: Int,
 *    price: Double,
 *    description: String,
 *    rooms: Int,
 *    squareMeters: Int,
 *    floor: Int,
 *    address: String,
 *    selectedServices: List<Int>,
 *    type: Int,
 *    numberOfTenants: Int,
 *    minContract: Int,
 *    maxContract: Int
 * ) → Result<Unit>”
 */
interface CreateRentalPostUseCase : suspend (
    Int,
    Double,
    String,
    Int,
    Int,
    Int,
    String,
    String,
    Int,
    Int,
    Int,
    Int
) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.createRentalPost()
 */
class CreateRentalPostUseCaseImpl(
    private val rentalRepository: RentalRepository
) : CreateRentalPostUseCase {
    override suspend fun invoke(
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        selectedServices: String,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> {
        return rentalRepository.createRentalPost(
            idArea,
            price,
            description,
            rooms,
            squareMeters,
            floor,
            address,
            selectedServices,
            type,
            numberOfTenants,
            minContract,
            maxContract
        )
    }
}
