package repositories

import db.DBController
import model.Programmer
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Jaime Salcedo
 * @see IRepository
 */
class ProgrammerRepository : IRepository<Programmer, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos Programmer
     * @author Jaime Salcedo
     * @return List<Programmer>
     */
    override fun findAll(): List<Programmer> {
        val query = "select * from Programmer"
        val programmers = ArrayList<Programmer>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all programmmers."
        )
        while (result.next()) {
            val programmer = getProgrammerFromResultSet(result)
            programmers.add(programmer)
        }
        DBController.close()
        return programmers
    }

    /**
     * Encuentra el programmer cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Programmer, si lo encuentra.
     * @author Jaime Salcedo
     * @param id String
     * @return Programmer
     */
    override fun getById(id: String): Programmer {
        val query = "select * from Programmer where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Programmer with id: $id")
        var programmmerResult : Programmer? = null
        if (result.next()) {
            programmmerResult = getProgrammerFromResultSet(result)
        }
        DBController.close()
        return programmmerResult ?: throw SQLException("Error: Programmer with id $id does not exist.")
    }

    /**
     * Crea un objeto programmer a partir del resultSet devuelto por el DBController
     * @author Jaime Salcedo
     * @param result ResultSet
     * @return Programmer
     * @see DBController
     */
    private fun getProgrammerFromResultSet(result: ResultSet): Programmer {
        return Programmer(
                result.getString("id"),
                result.getString("name"),
                result.getString("registerDate"),
                result.getString("department_id"),
                result.getString("activeProjects_ids"),
                result.getString("commits_ids"),
                result.getString("issues_ids"),
                result.getString("technologies"),
                result.getDouble("salary"),
                result.getInt("isDepBoss"),
                result.getInt("isProjectManager"),
                result.getInt("isActive")
        )
    }

    /**
     * Inserta un programmer en la base de datos, donde cada atributo del programmer va a un campo de la tabla programmer,
     * devolviendo dicho programmer si lo consigue.
     * @author Jaime Salcedo
     * @param programmer Programmer
     * @return Programmer
     */
    override fun insert(programmer: Programmer) : Programmer {
        val query = "insert into Programmer values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, programmer.id, programmer.name, programmer.registerDate,
                programmer.department_id, programmer.activeProjects_ids, programmer.commits_ids,
                programmer.issues_ids, programmer.technologies, programmer.salary,
                programmer.isDepBoss, programmer.isProjectManager, programmer.isActive
        ) ?: throw SQLException("Error: could not insert Programmer")
        if (result.next()) {
            DBController.close()
            return programmer
        }
        else throw SQLException("Error: could not insert Programmer into DB")
    }

    /**
     * Modifica un programmer, si existe, devolviendo dicho programmer si lo consigue.
     * @author Jaime Salcedo
     * @param programmer Programmer
     * @return Programmer
     */
    override fun update(programmer: Programmer) : Programmer {
        val query = ("update Programmer set name = ?, department_id = ?, activeProjects_ids = ?, " +
                "commits_ids = ?, issues_ids = ?, technologies = ?, salary = ?, isDepBoss = ?, " +
                "isProjectManager = ?, isActive = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, programmer.name, programmer.department_id, programmer.activeProjects_ids,
                programmer.commits_ids, programmer.issues_ids, programmer.technologies,
                programmer.salary, programmer.isDepBoss, programmer.isProjectManager,
                programmer.isActive, programmer.id
        )
        DBController.close()
        if (result > 0) {
            return programmer
        }
        else throw SQLException("Error: could not update Programmer with id ${programmer.id}")
    }

    /**
     * Borra un programmer, si existe, devolviendo dicho programmer si lo consigue.
     * @author Jaime Salcedo
     * @param programmer Programmer
     * @return Programmer
     */
    override fun delete(programmer: Programmer) : Programmer {
        val query = "delete from Programmer where id = ?"
        DBController.open()
        val result = DBController.delete(query, programmer.id)
        DBController.close()
        if (result > 0) {
            return programmer
        }
        else throw SQLException("Error: could not delete Programmer with id ${programmer.id}")
    }
}