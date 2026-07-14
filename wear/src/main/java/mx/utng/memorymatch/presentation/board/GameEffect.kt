package mx.utng.memorymatch.presentation.board

sealed class GameEffect {
    object HapticMatch : GameEffect()
    object HapticMiss : GameEffect()
    object HapticVictory : GameEffect()
}