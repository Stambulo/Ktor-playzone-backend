package md.playzone.database.users

import org.jetbrains.exposed.sql.Table

object UserModel: Table("users") {
    private val login = UserModel.varchar("login", 25)
    private val password = UserModel.varchar("password", 25)
    private val username = UserModel.varchar("username", 30)
    private val email = UserModel.varchar("email", 25)
}
