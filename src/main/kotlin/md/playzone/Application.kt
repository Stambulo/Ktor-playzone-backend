package md.playzone

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import md.playzone.features.games.configureGamesRouting
import md.playzone.features.login.configureLoginRouting
import md.playzone.features.register.configureRegisterRouting
import md.playzone.plugins.configureRouting
import md.playzone.plugins.configureSerialization
import org.jetbrains.exposed.sql.Database

fun main() {
    val config = HikariConfig("/hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    embeddedServer(
        Netty,
        port = System.getenv("SERVER_PORT").toInt(),
        module = Application::playzoneModule
        ).start(wait = true)
}

fun Application.playzoneModule() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureGamesRouting()
    configureSerialization()
}


//      + 2 tables
//      1:49:00
//      https://youtu.be/LqaBXrRkhC0?si=BFRGJzpOVKGTcK-m


//      hikari.properties 1:56:58