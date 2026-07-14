package mx.utng.memorymatch.presentation.board

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.utng.memorymatch.data.datasource.BestTimeDataSource
import mx.utng.memorymatch.data.repository.BestTimeRepositoryImpl
import mx.utng.memorymatch.domain.usecase.CheckMatchUseCase
import mx.utng.memorymatch.domain.usecase.FlipCardUseCase
import mx.utng.memorymatch.domain.usecase.GetBestTimeUseCase
import mx.utng.memorymatch.domain.usecase.SaveBestTimeUseCase
import mx.utng.memorymatch.domain.usecase.ShuffleBoardUseCase

class MemoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dataSource = BestTimeDataSource(context)
        val repository = BestTimeRepositoryImpl(dataSource)

        return MemoryViewModel(
            shuffleBoard = ShuffleBoardUseCase(),
            flipCard = FlipCardUseCase(),
            checkMatch = CheckMatchUseCase(),
            saveBestTime = SaveBestTimeUseCase(repository),
            getBestTime = GetBestTimeUseCase(repository),
        ) as T
    }
}