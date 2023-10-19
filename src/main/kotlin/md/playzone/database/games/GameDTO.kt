package md.playzone.database.games

import md.playzone.features.games.models.CreateGameRequest
import md.playzone.features.games.models.CreateGameResponse
import md.playzone.features.games.models.GameResponse
import java.util.UUID

data class GameDTO(
    val gameId: String,
    val name: String,
    val backdrop: String?,
    val logo: String,
    val description: String,
    val downloadCount: Int,
    val version: String,
    val weight: String
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        gameId = UUID.randomUUID().toString(),
        name = title,
        description = description,
        version = version,
        weight = size,
        backdrop = "",
        logo = "",
        downloadCount = 0
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = gameId,
        title = name,
        description = description,
        version = version,
        size = weight
    )

fun GameDTO.mapToGameResponse(): GameResponse = GameResponse(
    gameId = gameId,
    title = name,
    description = description,
    version = version,
    size = weight
)