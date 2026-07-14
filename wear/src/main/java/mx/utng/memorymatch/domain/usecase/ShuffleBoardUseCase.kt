package mx.utng.memorymatch.domain.usecase

import mx.utng.memorymatch.domain.model.Card
import mx.utng.memorymatch.domain.model.CardSymbol

class ShuffleBoardUseCase {
    /** Crea 12 tarjetas (2 de cada símbolo) mezcladas aleatoriamente. */
    operator fun invoke(): List<Card> =
        CardSymbol.values()
            .flatMap { symbol -> listOf(symbol, symbol) } // duplicar cada símbolo
            .shuffled()
            .mapIndexed { index, symbol -> Card(id = index, symbol = symbol) }
}