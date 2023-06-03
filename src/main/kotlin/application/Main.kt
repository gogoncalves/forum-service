package application

import application.configs.mysqlConnection
import mongoDBConnection

fun main(args: Array<String>) {
    println("Starting forum service!")
    mysqlConnection()
    mongoDBConnection("agora")
}