package md.playzone.features.games

import kotlinx.serialization.Serializable

@Serializable
data class FetchGameRequest(
    val token: String
)
