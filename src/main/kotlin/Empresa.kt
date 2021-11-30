import db.DBController
import java.io.File
import kotlin.system.exitProcess

object Empresa {
    fun checkService() {
        try {
            DBController.open()
            DBController.select("SELECT 'Working properly.'")?.first()
            DBController.close()
        }
        // esto no deberia saltar nunca, al ser un fichero sqlite.
        catch (e: Exception) {
            println("Could not connect with Database: ${e.stackTrace}")
            exitProcess(1)
        }
    }

    init {
        initializeDB()
    }

    private fun initializeDB() {
        val path = "${System.getProperty("user.dir")}${ File.separator}sql${ File.separator}database.sql"
        DBController.open()
        DBController.initData(path)
        DBController.close()
    }

    //TODO: TERMINAR ESTO
}