package md.playzone

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import md.playzone.features.login.configureLoginRouting
import md.playzone.features.register.configureRegisterRouting
import md.playzone.plugins.configureRouting
import md.playzone.plugins.configureSerialization
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/playzone",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Sa135244"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSerialization()
    }.start(wait = true)
}


//      1:23:00
//      https://www.youtube.com/watch?v=LqaBXrRkhC0&list=WL&index=17&t=1505s
