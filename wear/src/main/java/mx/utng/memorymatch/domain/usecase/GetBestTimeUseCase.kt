package mx.utng.memorymatch.domain.usecase

import mx.utng.memorymatch.domain.repository.BestTimeRepository

class GetBestTimeUseCase(private val repository: BestTimeRepository) {
    suspend operator fun invoke(): Long = repository.getBestTime()
}