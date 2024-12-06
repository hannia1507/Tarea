package com.example.polireport

import android.os.StrictMode
import java.sql.Connection
import java.sql.DriverManager
import android.util.Log
import java.sql.SQLException

class ConnectSql {
    private val ip = "192.168.0.108:60607" // IP y puerto del servidor SQL
    private val db = "SSP" // Nombre de la base de datos
    private val username = "LAP-CEN100" // Usuario de SQL Server
    private val password = "" // Contraseña del usuario

    // Método para obtener la conexión
    fun dbConn(): Connection? {
        var conn: Connection? = null
        try {
            // Configurar la política de ejecución para permitir operaciones en el hilo principal
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            // Registrar el driver para SQL Server
            Class.forName("net.sourceforge.jtds.jdbc.Driver")

            // Crear la cadena de conexión
            val connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password"

            // Establecer la conexión
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException) {
            Log.e("SQL Connection Error", ex.message ?: "Error desconocido")
        } catch (ex: ClassNotFoundException) {
            Log.e("Driver Error", "Driver JDBC no encontrado: ${ex.message}")
        } catch (ex: Exception) {
            Log.e("General Error", ex.message ?: "Error desconocido")
        }
        return conn
    }
}



