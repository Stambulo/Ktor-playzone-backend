package md.playzone

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import md.playzone.features.games.configureGamesRouting
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
        configureGamesRouting()
        configureSerialization()
    }.start(wait = true)
}


//      + 2 tables
//      1:49:00
//      https://youtu.be/LqaBXrRkhC0?si=BFRGJzpOVKGTcK-m
