package repositories

import db.DBController
import model.Project_Programmer
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class Project_ProgrammerRepository : IRepository<Project_Programmer, String> {
    override fun findAll(): List<Project_Programmer> {
        val query = "select * from Project_Programmer"
        val pps = ArrayList<Project_Programmer>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all project_programmers."
        )
        while (result.next()) {
            val pp = getProjectProgrammerFromResultSet(result)
            pps.add(pp)
        }
        DBController.close()
        return pps
    }

    override fun getById(id: String): Project_Programmer {
        val query = "select * from Project_Programmer where id = ?"
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Project_Programmer with id: $id")
        var pp : Project_Programmer? = null
        if (result.next()) {
            pp = getProjectProgrammerFromResultSet(result)
        }
        DBController.close()
        return pp ?: throw SQLException("Error: Project_Programmer with id $id does not exist.")
    }

    fun getByProgrammerId(id: String): List<Project_Programmer> {
        val query = "select * from Project_Programmer where programmer_id = ?"
        val pps = ArrayList<Project_Programmer>()
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Project_Programmer with programmer_id: $id")
        while (result.next()) {
            val pp = getProjectProgrammerFromResultSet(result)
            pps.add(pp)
        }
        DBController.close()
        return pps
    }

    fun getByProjectId(id: String): List<Project_Programmer> {
        val query = "select * from Project_Programmer where project_id = ?"
        val pps = ArrayList<Project_Programmer>()
        DBController.open()
        val result = DBController.select(query, id)
                ?: throw SQLException("Error while consulting Project_Programmer with project_id: $id")
        while (result.next()) {
            val pp = getProjectProgrammerFromResultSet(result)
            pps.add(pp)
        }
        DBController.close()
        return pps
    }

    private fun getProjectProgrammerFromResultSet(result: ResultSet): Project_Programmer {
        return Project_Programmer(
                result.getString("id"),
                result.getString("project_id"),
                result.getString("programmer_id")
        )
    }

    override fun insert(pp: Project_Programmer) : Project_Programmer {
        val query = "insert into Project_Programmer values (?, ?, ?)"
        DBController.open()
        val result = DBController.insert(query, pp.id, pp.project_id, pp.programmer_id)
                ?: throw SQLException("Error: could not insert Project_Programmer")
        if (result.next()) {
            DBController.close()
            return pp
        }
        else throw SQLException("Error: could not insert Project_Programmer into DB")
    }

    override fun update(pp: Project_Programmer) : Project_Programmer {
        val query = ("update Project_Programmer set project_id = ?, programmer_id = ? where id = ?")
        DBController.open()
        val result = DBController.update(query, pp.project_id, pp.programmer_id, pp.id)
        DBController.close()
        if (result > 0) {
            return pp
        }
        else throw SQLException("Error: could not update Project_Programmer with id ${pp.id}")
    }

    override fun delete(pp: Project_Programmer) : Project_Programmer {
        val query = "delete from Project_Programmer where id = ?"
        DBController.open()
        val result = DBController.delete(query, pp.id)
        DBController.close()
        if (result > 0) {
            return pp
        }
        else throw SQLException("Error: could not delete Project_Programmer with id ${pp.id}")
    }

    fun deleteAllWithProjectId(id: String) : Int {
        val query = "delete from Project_Programmer where project_id = ?"
        DBController.open()
        val result = DBController.delete(query, id)
        DBController.close()
        if (result > 0) {
            return result
        }
        else throw SQLException("Error: could not delete Project_Programmers with project_id $id")
    }

    fun deleteAllWithProgrammerId(id: String) : Int {
        val query = "delete from Project_Programmer where programmer_id = ?"
        DBController.open()
        val result = DBController.delete(query, id)
        DBController.close()
        if (result > 0) {
            return result
        }
        else throw SQLException("Error: could not delete Project_Programmers with programmer_id $id")
    }
}