package md.playzone.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGamesResponse(
    val games: List<GameResponse>
)
