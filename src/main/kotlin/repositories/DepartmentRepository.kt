package repositories

import db.DBController
import model.Department
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

/**
 * Clase encargada de hacer las operaciones CRUD de Repository.
 * @author Daniel Rodríguez
 * @see IRepository
 */
class DepartmentRepository : IRepository<Department, String> {
    /**
     * Encuentra todos los repositories presentes en la BD y los devuelve como una lista de objetos department
     * @author Daniel Rodríguez
     * @return List<Department>
     */
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

    /**
     * Encuentra el department cuyo ID casa con el parámetro introducido y lo devuelve como un objeto Department, si lo encuentra.
     * @author Daniel Rodríguez
     * @param id String
     * @return Department
     */
    override fun getById(id: String): Department {
        val query = "select * from Department where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Department with id: $id")
        var departmentResult : Department? = null
        if (result.next()) {
            departmentResult = getDepartmentFromResultSet(result)
        }
        DBController.close()
        return departmentResult ?: throw SQLException("Error: Department with id $id does not exist.")
    }

    /**
     * Crea un objeto department a partir del resultSet devuelto por el DBController
     * @author Daniel Rodríguez
     * @param result ResultSet
     * @return Department
     * @see DBController
     */
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

    /**
     * Inserta un department en la base de datos, donde cada atributo del department va a un campo de la tabla department,
     * devolviendo dicho department si lo consigue.
     * @author Daniel Rodríguez
     * @param department Department
     * @return Department
     */
    override fun insert(department: Department) : Department {
        val query = "insert into Department values (?, ?, ?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, department.id, department.name, department.boss_id,
                department.budget, department.finishedProjects_ids,
                department.developingProjects_ids, department.anualBudget,
                department.bossHistory_ids
        ) ?: throw SQLException("Error: could not insert Department")
        if (result.next()) {
            DBController.close()
            return department
        }
        else throw SQLException("Error: could not insert Department into DB")
    }

    /**
     * Modifica un department, si existe, devolviendo dicho department si lo consigue.
     * @author Daniel Rodríguez
     * @param department Department
     * @return Department
     */
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

    /**
     * Borra un department, si existe, devolviendo dicho department si lo consigue.
     * @author Daniel Rodríguez
     * @param department Department
     * @return Department
     */
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