# 🧠 Memory Match — Wear OS

Juego de memoria para Wear OS. El jugador voltea tarjetas de dos en dos buscando
pares de componentes Android (Compose, Room, Flow, StateFlow, ViewModel, DataLayer)
sobre una cuadrícula 3×4 adaptada a la pantalla circular del reloj.

## Alumnas
- Tapia Cid Laura Berenice

**Grupo:** GIDS6093 — UTNG

## Descripción

El tablero tiene 12 tarjetas (6 pares) boca abajo. El jugador toca una tarjeta,
se anima el flip y se revela el ícono/etiqueta; toca una segunda: si coinciden,
permanecen visibles; si no, se voltean de nuevo tras una pausa de 800ms.
Al completar todos los pares, se muestra el tiempo total y si es un nuevo récord.

## Stack y arquitectura

| Componente     | Tecnología                                                  |
|----------------|--------------------------------------------------------------|
| UI             | Compose for Wear OS — `LazyVerticalGrid` circular             |
| Arquitectura   | MVVM + Clean Architecture (Domain / Data / Presentation)      |
| Animación      | `animateFloatAsState` + `graphicsLayer` (rotación Y para flip)|
| Estado         | `GameState` inmutable + `StateFlow<GameState>`                |
| Persistencia   | DataStore Preferences (mejor tiempo)                          |
| Coroutines     | `viewModelScope.launch` para la pausa de 800ms                |
| Testing        | JUnit 4 — lógica de mezcla, flip y match, sin emulador         |

## Estructura del proyecto

```
wear/src/main/java/mx/utng/memorymatch/
├── domain/
│   ├── model/        → Card, CardSymbol, GameState, GamePhase, MatchResult
│   ├── usecase/       → ShuffleBoardUseCase, FlipCardUseCase, CheckMatchUseCase,
│   │                    SaveBestTimeUseCase, GetBestTimeUseCase
│   └── repository/    → BestTimeRepository (interfaz)
├── data/
│   ├── datasource/     → BestTimeDataSource (DataStore)
│   └── repository/     → BestTimeRepositoryImpl
├── presentation/board/ → MemoryViewModel, BoardScreen, CardItem, VictoryScreen
└── GameActivity.kt
```
## Cómo correr el proyecto

1. Clona el repositorio
2. Ábrelo en Android Studio
3. Crea/levanta un emulador Wear OS (recomendado: "Wear OS Large Round")
4. Corre el módulo `:wear` con la actividad `GameActivity`

## Tests

```bash
./gradlew :wear:test
```

## Buenas prácticas aplicadas

- Inmutabilidad (`GameState` con `.copy()`, nunca mutación directa)
- SRP por clase (flip / check / shuffle en use cases separados)
- Unidirectional Data Flow (UDF) con `StateFlow`
- `Channel` para efectos de una sola vez (haptics)
- Funciones puras en los casos de uso
- Tests sin emulador
- Separación entre animación (Composable) y lógica (ViewModel)
