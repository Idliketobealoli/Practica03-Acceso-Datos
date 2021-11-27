package repositories

import db.DBController
import model.Project
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class ProjectRepository : IRepository<Project> {
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

    override fun getById(id: String): Project {
        val query = "select * from Project where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Project with id: $id")
        var projectResult : Project? = null
        if (result.first()) {
            projectResult = getProjectFromResultSet(result)
        }
        DBController.close()
        return projectResult ?: throw SQLException("Error: Project with id $id does not exist.")
    }

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

    override fun insert(project: Project) : Project {
        val query = "insert into Project values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, project.id, project.department_id, project.projectManager_id,
                project.name, project.budget, project.startDate,
                project.endDate, project.technologies, project.repository_id,
                project.isFinished, project.programmers_ids
        ) ?: throw SQLException("Error: could not insert Project")
        if (result.first()) {
            DBController.close()
            return project
        }
        else throw SQLException("Error: could not insert Project into DB")
    }

    override fun update(project: Project) : Project {
        val query = ("update Project set department_id = ?, projectManager_id = ?, name = ?, " +
                "budget = ?, endDate = ?, technologies = ?, " +
                "isFinished = ?, programmers_ids = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, project.department_id, project.projectManager_id, project.name,
                project.budget, project.endDate, project.technologies,
                project.isFinished, project.programmers_ids, project.id
        )
        DBController.close()
        if (result > 0) {
            return project
        }
        else throw SQLException("Error: could not update Project with id ${project.id}")
    }

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