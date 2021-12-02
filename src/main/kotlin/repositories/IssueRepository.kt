package repositories

import db.DBController
import model.Issue
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Jaime Salcedo
 * @see IRepository
 */
class IssueRepository  : IRepository<Issue, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos Issue
     * @author Jaime Salcedo
     * @return List<Issue>
     */
    override fun findAll(): List<Issue> {
        val query = "select * from Issue"
        val issues = ArrayList<Issue>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all issues."
        )
        while (result.next()) {
            val issue = getIssueFromResultSet(result)
            issues.add(issue)
        }
        DBController.close()
        return issues
    }

    /**
     * Encuentra el issue cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Issue, si lo encuentra.
     * @author Jaime Salcedo
     * @param id String
     * @return Issue
     */
    override fun getById(id: String): Issue {
        val query = "select * from Issue where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Issue with id: $id")
        var issue : Issue? = null
        if (result.next()) {
            issue = getIssueFromResultSet(result)
        }
        DBController.close()
        return issue ?: throw SQLException("Error: Issue with id $id does not exist.")
    }

    /**
     * Crea un objeto issue a partir del resultSet devuelto por el DBController
     * @author Jaime Salcedo
     * @param result ResultSet
     * @return Issue
     * @see DBController
     */
    private fun getIssueFromResultSet(result: ResultSet): Issue {
        return Issue(
                result.getString("id"),
                result.getString("author_id"),
                result.getString("title"),
                result.getString("text"),
                result.getString("date"),
                result.getString("programmers_ids"),
                result.getString("project_id"),
                result.getString("repository_id"),
                result.getInt("isFinished")
        )
    }

    /**
     * Inserta un issue en la base de datos, donde cada atributo del issue va a un campo de la tabla issue,
     * devolviendo dicho issue si lo consigue.
     * @author Jaime Salcedo
     * @param issue Issue
     * @return Issue
     */
    override fun insert(issue: Issue) : Issue {
        val query = "insert into Issue values (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, issue.id, issue.author_id, issue.title,
                issue.text, issue.date, issue.programmers_ids,
                issue.project_id, issue.repository_id, issue.isFinished
        ) ?: throw SQLException("Error: could not insert Issue")
        if (result.next()) {
            DBController.close()
            return issue
        }
        else throw SQLException("Error: could not insert Issue into DB")
    }

    /**
     * Modifica un issue, si existe, devolviendo dicho issue si lo consigue.
     * @author Jaime Salcedo
     * @param issue Issue
     * @return Issue
     */
    override fun update(issue: Issue) : Issue {
        val query = ("update Issue set author_id = ?, title = ?, text = ?, " +
                "date = ?, programmers_ids = ?, project_id = ?, repository_id = ?, " +
                "isFinished = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, issue.author_id, issue.title, issue.text,
                issue.date, issue.programmers_ids, issue.project_id,
                issue.repository_id, issue.isFinished, issue.id
        )
        DBController.close()
        if (result > 0) {
            return issue
        }
        else throw SQLException("Error: could not update Issue with id ${issue.id}")
    }

    /**
     * Borra un issue, si existe, devolviendo dicho issue si lo consigue.
     * @author Jaime Salcedo
     * @param issue Issue
     * @return Issue
     */
    override fun delete(issue: Issue) : Issue {
        val query = "delete from Issue where id = ?"
        DBController.open()
        val result = DBController.delete(query, issue.id)
        DBController.close()
        if (result > 0) {
            return issue
        }
        else throw SQLException("Error: could not delete Issue with id ${issue.id}")
    }
}