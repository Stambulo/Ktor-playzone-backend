package md.playzone.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import md.playzone.database.games.Games
import md.playzone.database.games.mapToCreateGameResponse
import md.playzone.database.games.mapToGameDTO
import md.playzone.database.games.mapToGameResponse
import md.playzone.features.games.models.CreateGameRequest
import md.playzone.features.games.models.FetchGamesRequest
import md.playzone.features.games.models.FetchGamesResponse
import md.playzone.utils.TokenCheck

class GamesController(private val call: ApplicationCall) {

    suspend fun performSearch() {
        val request = call.receive<FetchGamesRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
            call.respond(FetchGamesResponse(
                games = Games.fetchGames().filter { it.name.contains(request.searchQuery, ignoreCase = true) }
                    .map { it.mapToGameResponse() }
            ))
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun createGame() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateGameRequest>()
            val game = request.mapToGameDTO()
            Games.insert(game)
            call.respond(game.mapToCreateGameResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}
