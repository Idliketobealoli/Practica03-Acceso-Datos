package repositories

import db.DBController
import model.Programmer_Issue
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Daniel Rodríguez
 * @see IRepository
 */
class Programmer_IssueRepository : IRepository<Programmer_Issue, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos Programmer_Issue
     * @author Daniel Rodríguez
     * @return List<Programmer_Issue>
     */
    override fun findAll(): List<Programmer_Issue> {
        val query = "select * from Programmer_Issue"
        val pis = ArrayList<Programmer_Issue>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all programmer_issues."
        )
        while (result.next()) {
            val pi = getProgrammerIssueFromResultSet(result)
            pis.add(pi)
        }
        DBController.close()
        return pis
    }

    /**
     * Encuentra el programmer_issue cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Programmer_Issue, si lo encuentra.
     * @author Daniel Rodríguez
     * @param id String
     * @return Programmer_Issue
     */
    override fun getById(id: String): Programmer_Issue {
        val query = "select * from Programmer_Issue where id = ?"
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Programmer_Issue with id: $id")
        var pp : Programmer_Issue? = null
        if (result.next()) {
            pp = getProgrammerIssueFromResultSet(result)
        }
        DBController.close()
        return pp ?: throw SQLException("Error: Programmer_Issue with id $id does not exist.")
    }

    /**
     * Encuentra el programmer_issue cuyo programmer_ID casa con el parámetro introducido y lo devuelve como una lista de objetos Programmer_Issue, si lo encuentra.
     * @author Daniel Rodríguez
     * @param id String
     * @return List<Programmer_Issue>
     */
    fun getByProgrammerId(id: String) : List<Programmer_Issue> {
        val query = "select * from Programmer_Issue where programmer_id = ?"
        val pis = ArrayList<Programmer_Issue>()
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Programmer_Issue with programmer_id: $id")
        while (result.next()) {
            val pi = getProgrammerIssueFromResultSet(result)
            pis.add(pi)
        }
        DBController.close()
        return pis
    }

    /**
     * Encuentra el programmer_issue cuyo issue_ID casa con el parámetro introducido y lo devuelve como una lista de objetos Programmer_Issue, si lo encuentra.
     * @author Daniel Rodríguez
     * @param id String
     * @return List<Programmer_Issue>
     */
    fun getByIssueId(id: String): List<Programmer_Issue> {
        val query = "select * from Programmer_Issue where issue_id = ?"
        val pis = ArrayList<Programmer_Issue>()
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Programmer_Issue with issue_id: $id")
        while (result.next()) {
            val pi = getProgrammerIssueFromResultSet(result)
            pis.add(pi)
        }
        DBController.close()
        return pis
    }

    /**
     * Crea un objeto Programmer_Issue a partir del resultSet devuelto por el DBController
     * @author Daniel Rodríguez
     * @param result ResultSet
     * @return Programmer_Issue
     * @see DBController
     */
    private fun getProgrammerIssueFromResultSet(result: ResultSet): Programmer_Issue {
        return Programmer_Issue(
                result.getString("id"),
                result.getString("programmer_id"),
                result.getString("issue_id")
        )
    }

    /**
     * Inserta un programmer_issue en la base de datos, donde cada atributo del programmer_issue va a un campo de la tabla programmer_issue,
     * devolviendo dicho programmer_issue si lo consigue.
     * @author Daniel Rodríguez
     * @param pi Programmer_Issue
     * @return Programmer_Issue
     */
    override fun insert(pi: Programmer_Issue): Programmer_Issue {
        val query = "insert into Programmer_Issue values (?, ?, ?)"
        DBController.open()
        val result = DBController.insert(query, pi.id, pi.programmer_id, pi.issue_id)
                ?: throw SQLException("Error: could not insert Programmer_Issue.")
        if (result.next()) {
            DBController.close()
            return pi
        }
        else throw SQLException("Error: could not insert Programmer_Issue into DB")

    }

    /**
     * Modifica un programmer_issue, si existe, devolviendo dicho programmer_issue si lo consigue.
     * @author Daniel Rodríguez
     * @param pi Programmer_Issue
     * @return Programmer_Issue
     */
    override fun update(pi: Programmer_Issue): Programmer_Issue {
        val query = ("update Programmer_Issue set programmer_id = ?, issue_id = ? where id = ?")
        DBController.open()
        val result = DBController.update(query, pi.programmer_id, pi.issue_id, pi.id)
        DBController.close()
        if (result > 0) {
            return pi
        }
        else throw SQLException("Error: could not update Programmer_Issue with id ${pi.id}")

    }

    /**
     * Borra un programmer_issue, si existe, devolviendo dicho programmer_issue si lo consigue.
     * @author Daniel Rodríguez
     * @param pi Programmer_Issue
     * @return Programmer_Issue
     */
    override fun delete(pi: Programmer_Issue): Programmer_Issue {
        val query = "delete from Programmer_Issue where id = ?"
        DBController.open()
        val result = DBController.delete(query, pi.id)
        DBController.close()
        if (result > 0) {
            return pi
        }
        else throw SQLException("Error: could not delete Programmer_Issue with id ${pi.id}")
    }

    /**
     * Borra los programmer_issue cuyo programmer_id case con el parámetro que le pasemos, si existe,
     * devolviendo un Int.
     * @author Daniel Rodríguez
     * @param  id String
     * @return Int
     */
    fun deleteAllWithProgrammerId(id: String) : Int {
        val query = "delete from Programmer_Issue where programmer_id = ?"
        DBController.open()
        val result = DBController.delete(query, id)
        DBController.close()
        if (result > 0) {
            return result
        }
        else throw SQLException("Error: could not delete Programmer_Issue with programmer_id $id")
    }

    /**
     * Borra los programmer_issue cuyo issue_id case con el parámetro que le pasemos, si existe,
     * devolviendo un Int.
     * @author Daniel Rodríguez
     * @param  id String
     * @return Int
     */
    fun deleteAllWithIssueId(id: String) : Int {
        val query = "delete from Programmer_Issue where issue_id = ?"
        DBController.open()
        val result = DBController.delete(query, id)
        DBController.close()
        if (result > 0) {
            return result
        }
        else throw SQLException("Error: could not delete Programmer_Issue with issue_id $id")
    }
}