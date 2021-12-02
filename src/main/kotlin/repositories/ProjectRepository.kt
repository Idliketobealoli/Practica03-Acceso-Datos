package repositories

import db.DBController
import model.Project
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Jaime Salcedo
 * @see IRepository
 */
class ProjectRepository : IRepository<Project, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos Project
     * @author Jaime Salcedo
     * @return List<Project>
     */
    override fun findAll(): List<Project> {
        val query = "select * from Project"
        val projects = ArrayList<Project>()
        DBController.open()
        val result = DBController.select(query)
                ?: throw SQLException("Error at selecting all projects.")
        while (result.next()) {
            val project = getProjectFromResultSet(result)
            projects.add(project)
        }
        DBController.close()
        return projects
    }

    /**
     * Encuentra el project cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Project, si lo encuentra.
     * @author Jaime Salcedo
     * @param id String
     * @return Project
     */
    override fun getById(id: String): Project {
        val query = "select * from Project where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Project with id: $id")
        var projectResult : Project? = null
        if (result.next()) {
            projectResult = getProjectFromResultSet(result)
        }
        DBController.close()
        return projectResult ?:
        throw SQLException("Error: Project with id $id does not exist.")
    }

    /**
     * Crea un objeto project a partir del resultSet devuelto por el DBController
     * @author Jaime Salcedo
     * @param result ResultSet
     * @return Project
     * @see DBController
     */
    private fun getProjectFromResultSet(result: ResultSet): Project {
        return Project(
                result.getString("id"),
                result.getString("department_id"),
                result.getString("projectManager_id"),
                result.getString("name"),
                result.getDouble("budget"),
                result.getString("startDate"),
                result.getString("endDate"),
                result.getString("technologies"),
                result.getString("repository_id"),
                result.getInt("isFinished"),
                result.getString("programmers_ids")
        )
    }

    /**
     * Inserta un project en la base de datos, donde cada atributo del project va a un campo de la tabla project,
     * devolviendo dicho project si lo consigue.
     * @author Jaime Salcedo
     * @param project Project
     * @return Project
     */
    override fun insert(project: Project) : Project {
        val query = "insert into Project values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, project.id, project.department_id, project.projectManager_id,
                project.name, project.budget, project.startDate,
                project.endDate, project.technologies, project.repository_id,
                project.isFinished, project.programmers_ids
        ) ?: throw SQLException("Error: could not insert Project")
        if (result.next()) {
            DBController.close()
            return project
        }
        else throw SQLException("Error: could not insert Project into DB")
    }

    /**
     * Modifica un project, si existe, devolviendo dicho project si lo consigue.
     * @author Jaime Salcedo
     * @param project Project
     * @return Project
     */
    override fun update(project: Project) : Project {
        val query = ("update Project set projectManager_id = ?, name = ?, " +
                "budget = ?, endDate = ?, technologies = ?, " +
                "isFinished = ?, programmers_ids = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, project.projectManager_id, project.name,
                project.budget, project.endDate, project.technologies,
                project.isFinished, project.programmers_ids, project.id
        )
        DBController.close()
        if (result > 0) {
            return project
        }
        else throw SQLException("Error: could not update Project with id ${project.id}")
    }

    /**
     * Borra un project, si existe, devolviendo dicho project si lo consigue.
     * @author Jaime Salcedo
     * @param project Project
     * @return Project
     */
    override fun delete(project: Project) : Project {
        val query = "delete from Project where id = ?"
        DBController.open()
        val result = DBController.delete(query, project.id)
        DBController.close()
        if (result > 0) {
            return project
        }
        else throw SQLException("Error: could not delete Project with id ${project.id}")
    }
}