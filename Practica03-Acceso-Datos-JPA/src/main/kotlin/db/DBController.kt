package db

import java.io.*
import java.sql.*
import java.sql.DriverManager
import java.sql.PreparedStatement
import kotlin.jvm.Throws
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * El controlador de la base de datos. Es el encargado de conectarse a la base de datos, cerrar la sesion con ella y
 * ejecutar las consultas SQL.
 */
object DBController {
    /*
    // Esto en caso de no usar SQLite:
    private var serverURL: String? = null
    private var serverPort: String? = null
    private var userName: String? = null
    private var password: String? = null
    private var databaseName: String? = null
     */
    private var jdbcDriver: String? = null
    private var connection: Connection? = null
    private var preparedStatement: PreparedStatement? = null

    // Esto solo en caso de usar SQLite:
    private var path: String? = null
    private var bbdd: String? = null

    /**
     * Parametros iniciales: driverJDBC y path a la base de datos SQLite
     */
    init {
        /*
        // Esto en caso de no usar SQLite:
        serverURL = "localhost"
        serverPort = ""
        userName = "JaimeLoli"
        password = "1707"
        databaseName = "Practica03DataBase"
         */
        jdbcDriver = "org.sqlite.JDBC"

        // Esto solo en caso de usar SQLite:
        path = "${System.getProperty("user.dir")}${File.separator}database${File.separator}"
        bbdd = "database.sqlite"
    }

    /**
     * abre una conexion con la base de datos.
     */
    @Throws(SQLException::class)
    fun open() {
        /*
        // Para mariadb:
        val url = "jdbc:mariadb://$serverURL:$serverPort${File.separator}$databaseName"
        connection = DriverManager.getConnection(url, userName, password)
         */

        // Para SQLite:
        val url = "jdbc:sqlite:$path$bbdd"
        connection = DriverManager.getConnection(url)
    }

    /**
     * cierra el preparedStatement y la conexion si existen.
     */
    @Throws(SQLException::class)
    fun close() {
        preparedStatement?.close()
        connection?.close()
    }

    /**
     * Coje la string querySQL, la convierte en preparedStatement, le mete los argumentos y ejecuta la query
     */
    @Throws(SQLException::class)
    private fun executeQuery(querySQL: String, vararg args: Any): ResultSet {
        preparedStatement = connection?.prepareStatement(querySQL)
        for (i in args.indices) {
            preparedStatement!!.setObject(i+1, args[i])
        }
        return preparedStatement!!.executeQuery()
    }

    /**
     * hace un select de la query que le pases con los orgumentos introducidos
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, vararg args: Any?): ResultSet? {
        return executeQuery(querySQL, *args as Array<out Any>)
    }

    /**
     * hace un select, pero con un limit y un offset.
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, limit: Int, offset: Int, vararg args: Any?): ResultSet? {
        val query = "$querySQL LIMIT $limit OFFSET $offset"
        return executeQuery(query, *args as Array<out Any>)
    }

    /**
     * Coge la sentencia sql, la convierte en un preparedStatement, le mete los argumentos y devuelve el ResultSet de
     * la consulta (insert)
     */
    @Throws(SQLException::class)
    fun insert(insertSQL: String?, vararg args: Any?): ResultSet? {
        preparedStatement = connection?.prepareStatement(insertSQL)
        for (i in args.indices) {
            preparedStatement!!.setObject(i + 1, args[i])
        }
        preparedStatement!!.executeUpdate()
        return preparedStatement!!.generatedKeys
    }

    /**
     * Llama al metodo updateQuery para ejecutar la sentencia SQL introducida.
     */
    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg args: Any?): Int {
        return updateQuery(updateSQL, *args as Array<out Any>)
    }

    /**
     * Literalmente hace lo mismo que el metodo update, solo que con el nombre "delete" para hacer que el codigo sea
     * más legible en los repositories.
     */
    @Throws(SQLException::class)
    fun delete(deleteSQL: String, vararg args: Any?): Int {
        return updateQuery(deleteSQL, *args as Array<out Any>)
    }

    /**
     * Metodo usado para updates y deletes. Se traga la secuencia SQL del primer parametro, la convierte en un
     * preparedStatement y le añade como argumentos los otros parametros. Tras ello, ejecuta la query.
     */
    @Throws(SQLException::class)
    private fun updateQuery(sqlSentence: String, vararg args: Any): Int {
        preparedStatement = connection?.prepareStatement(sqlSentence)
        for (i in args.indices) {
            preparedStatement!!.setObject(i + 1, args[i])
        }
        return preparedStatement!!.executeUpdate()
    }

    /**
     * Configuracion inicial del controlador. Corre el script del fichero ubicado en el path que le pasemos.
     */
    @Throws(SQLException::class)
    fun initData(sqlPath: String) {
        val scriptRunner = ScriptRunner(connection)
        scriptRunner.setEscapeProcessing(false)
        val reader = BufferedReader(FileReader(sqlPath))
        scriptRunner.runScript(reader)
    }
}