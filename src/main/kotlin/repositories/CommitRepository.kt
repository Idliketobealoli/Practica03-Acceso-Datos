package repositories

import db.DBController
import model.Commit
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Daniel Rodríguez
 * @see IRepository
 */
class CommitRepository : IRepository<Commit, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos commit
     * @author Daniel Rodríguez
     * @return List<Commit>
     */
    override fun findAll(): List<Commit> {
        val query = "select * from Commite"
        val commits = ArrayList<Commit>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all commits."
        )
        while (result.next()) {
            val commit = getCommitFromResultSet(result)
            commits.add(commit)
        }
        DBController.close()
        return commits
    }

    /**
     * Encuentra el commit cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Commit, si lo encuentra.
     * @author Daniel Rodríguez
     * @param id String
     * @return Commit
     */
    override fun getById(id: String): Commit {
        val query = "select * from Commite where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Commit with id: $id")
        var commit : Commit? = null
        if (result.next()) {
            commit = getCommitFromResultSet(result)
        }
        DBController.close()
        return commit ?: throw SQLException("Error: Commit with id $id does not exist.")
    }

    /**
     * Crea un objeto commit a partir del resultSet devuelto por el DBController
     * @author Daniel Rodríguez
     * @param result ResultSet
     * @return Commit
     * @see DBController
     */
    private fun getCommitFromResultSet(result: ResultSet): Commit {
        return Commit(
                result.getString("id"),
                result.getString("title"),
                result.getString("text"),
                result.getString("date"),
                result.getString("repository_id"),
                result.getString("project_id"),
                result.getString("author_id"),
                result.getString("issue_id")
        )
    }

    /**
     * Inserta un commit en la base de datos, donde cada atributo del commit va a un campo de la tabla commit,
     * devolviendo dicho commit si lo consigue.
     * @author Daniel Rodríguez
     * @param commit Commit
     * @return Commit
     */
    override fun insert(commit: Commit) : Commit {
        val query = "insert into Commite values (?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, commit.id, commit.title, commit.text,
                commit.date, commit.repository_id, commit.project_id,
                commit.author_id, commit.issue_id
        ) ?: throw SQLException("Error: could not insert Commit")
        if (result.next()) {
            DBController.close()
            return commit
        }
        else throw SQLException("Error: could not insert Commit into DB")
    }

    /**
     * Modifica un commit, si existe, devolviendo dicho commit si lo consigue.
     * @author Daniel Rodríguez
     * @param commit Commit
     * @return Commit
     */
    override fun update(commit: Commit) : Commit {
        val query = ("update Commite set title = ?, text = ?, date = ?, " +
                "repository_id = ?, project_id = ?, author_id = ?, issue_id = ? " +
                "where id = ?")
        DBController.open()
        val result = DBController.update(
                query, commit.title, commit.text, commit.date,
                commit.repository_id, commit.project_id, commit.author_id,
                commit.issue_id, commit.id
        )
        DBController.close()
        if (result > 0) {
            return commit
        }
        else throw SQLException("Error: could not update Commit with id ${commit.id}")
    }

    /**
     * Borra un commit, si existe, devolviendo dicho commit si lo consigue.
     * @author Daniel Rodríguez
     * @param commit Commit
     * @return Commit
     */
    override fun delete(commit: Commit) : Commit {
        val query = "delete from Commite where id = ?"
        DBController.open()
        val result = DBController.delete(query, commit.id)
        DBController.close()
        if (result > 0) {
            return commit
        }
        else throw SQLException("Error: could not delete Commit with id ${commit.id}")
    }
}