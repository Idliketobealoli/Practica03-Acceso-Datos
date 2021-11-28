package repositories

import db.DBController
import model.Programmer
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class ProgrammerRepository : IRepository<Programmer, String> {
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