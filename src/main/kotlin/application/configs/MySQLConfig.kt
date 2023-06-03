package application.configs

import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties
import java.util.logging.Level
import java.util.logging.Logger

fun mysqlConnection(): Connection? {
    val properties = Properties()
    val classLoader = Thread.currentThread().contextClassLoader
    val resourceStream = classLoader.getResourceAsStream("config.properties")
    val reader = resourceStream?.let { InputStreamReader(it, StandardCharsets.UTF_8) }
    properties.load(reader)

    val jdbcUrl = properties.getProperty("jdbcUrl")
    val driver = properties.getProperty("driver")
    val user = properties.getProperty("user")
    val password = properties.getProperty("password")

    return try {
        Class.forName(driver)
        DriverManager.getConnection(jdbcUrl, user, password).also {
            Logger.getLogger("mysqlConnection").log(Level.INFO, "Successfully connected to the MySQL database!")
        }
    } catch (e: ClassNotFoundException) {
        Logger.getLogger("mysqlConnection").log(Level.SEVERE, "Error loading MySQL JDBC Driver: ${e.message}")
        null
    } catch (e: SQLException) {
        Logger.getLogger("mysqlConnection").log(Level.SEVERE, "Error connecting to the MySQL database: ${e.message}")
        null
    }
}
