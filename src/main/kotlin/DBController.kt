import java.io.File
import java.sql.*
import java.sql.DriverManager
import java.sql.PreparedStatement
import kotlin.jvm.Throws

//TODO: MIRAR TUTORIAL DE COMO CONECTAR ESTE CONTROLADOR A UNA BASE DE DATOS SQLITE

object DBController {
    /*
    private var serverURL: String? = null
    private var serverPort: String? = null
    private var userName: String? = null
    private var password: String? = null
    private var databaseName: String? = null
     */
    private var jdbcDriver: String? = null
    private var connection: Connection? = null
    private var preparedStatement: PreparedStatement? = null

    private var path: String? = null
    private var bbdd: String? = null

    init {
        /*
        serverURL = "localhost"
        serverPort = ""
        userName = "JaimeLoli"
        password = "1707"
        databaseName = "Practica03DataBase"
         */
        jdbcDriver = "org.sqlite.JDBC"

        path = "${System.getProperty("user.dir")}${File.separator}database"
        bbdd = "${File.separator}db"
    }

    @Throws(SQLException::class)
    fun open() {
        val url = "jdbc:sqlite:$path$bbdd"
        connection = DriverManager.getConnection(url)
    }

    @Throws(SQLException::class)
    fun close() {
        preparedStatement?.close()
        connection?.close()
    }

    @Throws(SQLException::class)
    fun executeQuery(querySQL: String, vararg args: Any): ResultSet {
        preparedStatement = connection?.prepareStatement(querySQL)
        for (i in args.indices) {
            preparedStatement!!.setObject(i+1, args[i])
        }
        return preparedStatement!!.executeQuery()
    }

    @Throws(SQLException::class)
    fun select(querySQL: String, vararg args: Any?): ResultSet? {
        return executeQuery(querySQL, *args as Array<out Any>)
    }

    @Throws(SQLException::class)
    fun select(querySQL: String, limit: Int, offset: Int, vararg args: Any?): ResultSet? {
        val query = "$querySQL LIMIT $limit OFFSET $offset"
        return executeQuery(query, *args as Array<out Any>)
    }

    @Throws(SQLException::class)
    fun insert(insertSQL: String?, vararg args: Any?): ResultSet? {
        preparedStatement = connection?.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)
        for (i in args.indices) {
            preparedStatement!!.setObject(i + 1, args[i])
        }
        preparedStatement!!.executeUpdate()
        return preparedStatement!!.generatedKeys
    }

    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg args: Any?): Int {
        return updateQuery(updateSQL, *args as Array<out Any>)
    }

    @Throws(SQLException::class)
    fun delete(deleteSQL: String, vararg args: Any?): Int {
        return updateQuery(deleteSQL, *args as Array<out Any>)
    }

    @Throws(SQLException::class)
    private fun updateQuery(sqlSentence: String, vararg args: Any): Int {
        preparedStatement = connection?.prepareStatement(sqlSentence)
        for (i in args.indices) {
            preparedStatement!!.setObject(i + 1, args[i])
        }
        return preparedStatement!!.executeUpdate()
    }
}