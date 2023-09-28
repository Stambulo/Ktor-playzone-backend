package md.playzone.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import md.playzone.database.tokens.TokenDTO
import md.playzone.database.tokens.Tokens
import md.playzone.database.users.UserDTO
import md.playzone.database.users.Users
import md.playzone.utils.isValidEmail
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, message = "Email is not valid!")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exist")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }

            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}
