import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import java.util.logging.Level
import java.util.logging.Logger

fun mongoDBConnection(databaseName: String): MongoDatabase? {
    val connectionString = "mongodb://root:root@localhost:27017/$databaseName"
    val mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(connectionString))
        .build()

    return try {
        val mongoClient = MongoClients.create(mongoClientSettings)
        val database = mongoClient.getDatabase(databaseName)
        Logger.getLogger("mongoDBConnection").log(Level.INFO, "Successfully connected to the MongoDB database!")
        database
    } catch (e: Exception) {
        Logger.getLogger("mongoDBConnection").log(Level.INFO, "Error connecting to MongoDB: ${e.message}")
        null
    }
}
