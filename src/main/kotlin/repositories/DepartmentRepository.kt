package repositories

import db.DBController
import model.Department
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class DepartmentRepository : IRepository<Department, String> {
    override fun findAll(): List<Department> {
        val query = "select * from Department"
        val departments = ArrayList<Department>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all departments."
        )
        while (result.next()) {
            val department = getDepartmentFromResultSet(result)
            departments.add(department)
        }
        DBController.close()
        return departments
    }

    override fun getById(id: String): Department {
        val query = "select * from Department where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Department with id: $id")
        var departmentResult : Department? = null
        if (result.first()) {
            departmentResult = getDepartmentFromResultSet(result)
        }
        DBController.close()
        return departmentResult ?: throw SQLException("Error: Department with id $id does not exist.")
    }

    private fun getDepartmentFromResultSet(result: ResultSet): Department {
        return Department(
                result.getString("id"),
                result.getString("name"),
                result.getString("boss_id"),
                result.getDouble("budget"),
                result.getString("finishedProjects_ids"),
                result.getString("developingProjects_ids"),
                result.getDouble("anualBudget"),
                result.getString("bossHistory_ids"),
        )
    }

    override fun insert(department: Department) : Department {
        val query = "insert into Department values (?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, department.id, department.name, department.boss_id,
                department.budget, department.finishedProjects_ids,
                department.developingProjects_ids, department.anualBudget,
                department.bossHistory_ids
        ) ?: throw SQLException("Error: could not insert Department")
        if (result.first()) {
            DBController.close()
            return department
        }
        else throw SQLException("Error: could not insert Department into DB")
    }

    override fun update(department: Department) : Department {
        val query = ("update Department set name = ?, boss_id = ?, budget = ?, " +
                "finishedProjects_ids = ?, developingProjects_ids = ?, " +
                "anualBudget = ?, bossHistory_ids = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, department.name, department.boss_id, department.budget,
                department.finishedProjects_ids, department.developingProjects_ids,
                department.anualBudget, department.bossHistory_ids, department.id
        )
        DBController.close()
        if (result > 0) {
            return department
        }
        else throw SQLException("Error: could not update department with id ${department.id}")
    }

    override fun delete(department: Department) : Department {
        val query = "delete from Department where id = ?"
        DBController.open()
        val result = DBController.delete(query, department.id)
        DBController.close()
        if (result > 0) {
            return department
        }
        else throw SQLException("Error: could not delete department with id ${department.id}")
    }
}