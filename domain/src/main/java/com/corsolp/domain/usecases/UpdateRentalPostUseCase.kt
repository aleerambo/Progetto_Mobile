package com.corsolp.domain.usecases

import com.corsolp.domain.repository.RentalRepository

/**
 * Interfaccia: invocabile come “(
 *    id: Int,
 *    idArea: Int,
 *    price: Double,
 *    description: String,
 *    rooms: Int,
 *    squareMeters: Int,
 *    floor: Int,
 *    address: String,
 *    type: Int,
 *    numberOfTenants: Int,
 *    minContract: Int,
 *    maxContract: Int
 * ) → Result<Unit>”
 */
interface UpdateRentalPostUseCase : suspend (
    Int,
    Int,
    Double,
    String,
    Int,
    Int,
    Int,
    String,
    Int,
    Int,
    Int,
    Int
) -> Result<Unit>

/**
 * Implementazione: chiama rentalRepository.updateRentalPost()
 */
class UpdateRentalPostUseCaseImpl(
    private val rentalRepository: RentalRepository
) : UpdateRentalPostUseCase {
    override suspend fun invoke(
        id: Int,
        idArea: Int,
        price: Double,
        description: String,
        rooms: Int,
        squareMeters: Int,
        floor: Int,
        address: String,
        type: Int,
        numberOfTenants: Int,
        minContract: Int,
        maxContract: Int
    ): Result<Unit> {
        return rentalRepository.updateRentalPost(
            id,
            idArea,
            price,
            description,
            rooms,
            squareMeters,
            floor,
            address,
            type,
            numberOfTenants,
            minContract,
            maxContract
        )
    }
}
