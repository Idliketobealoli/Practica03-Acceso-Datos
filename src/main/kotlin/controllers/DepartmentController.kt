package controllers

import com.google.gson.GsonBuilder
import dto.DepartmentDTO
import services.DepartmentService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al DepartmentService y mostrar lo que reciba como xml o json
 * @author Daniel Rodriguez
 */
object DepartmentController {
    private val service = DepartmentService()
    /**
     * Este metodo muestra todos los department como xml o json dependiendo del parámetro que le pases
     * @author Daniel Rodriguez
     * @param returnMode String
     * @return String
     */
    fun findAllDepartments(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllDepartmentsXML()
            "JSON" -> findAllDepartmentsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todos los department y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     */
    private fun findAllDepartmentsXML() : String {
        val list = service.getAllDepartments()
        list.forEach { x -> Jaxb.departmentToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todos los department y las muestra como json
     * @author Daniel Rodriguez
     * @return String json
     */
    private fun findAllDepartmentsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllDepartments()) ?:
        throw SQLException("Error at DepartmentController.findAllDepartmentsJSON")
    }

    /**
     * Este metodo coge todos los department dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Daniel Rodriguez
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getDepartmentById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getDepartmentByIdXML(id)
            "JSON" -> getDepartmentByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todos los department dependiendo de un id que le pasemos y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     * @param id String
     */
    private fun getDepartmentByIdXML(id: String): String {
        val res = service.getDepartmentById(id)
        Jaxb.departmentToXML(res)
        return ""
    }

    /**
     * Este metodo coge todos los department dependiendo de un id que le pasemos y las muestra como json
     * @author Daniel Rodriguez
     * @param id String
     * @return String json
     */
    private fun getDepartmentByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getDepartmentById(id)) ?:
        throw SQLException("Error at DepartmentController.getDepartmentByIdJSON:" +
                " Department with id $id not found.")
    }

    /**
     * Este metodo inserta un department y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @param returnMode String
     * @return String
     */
    fun insertDepartment(dep : DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertDepartmentXML(dep)
            "JSON" -> insertDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta un department y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String xml
     */
    private fun insertDepartmentXML(dep: DepartmentDTO): String {
        val res = service.createDepartment(dep)
        Jaxb.departmentToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un department y lo muestra como json
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String json
     */
    private fun insertDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.insertDepartmentJSON:" +
                " Could not insert Department with id ${dep.id}")

    }

    /**
     * Este metodo actualiza un department y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @param returnMode String
     * @return String
     */
    fun updateDepartment(dep: DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateDepartmentXML(dep)
            "JSON" -> updateDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza un department y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String xml
     */
    private fun updateDepartmentXML(dep: DepartmentDTO): String {
        val res = service.updateDepartment(dep)
        Jaxb.departmentToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un department y lo muestra como json
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String json
     */
    private fun updateDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.updateDepartmentJSON:" +
                " Could not update Department with id ${dep.id}")

    }

    /**
     * Este metodo elimina un department y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @param returnMode String
     * @return String
     */
    fun deleteDepartment(dep : DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteDepartmentXML(dep)
            "JSON" -> deleteDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina un department y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String xml
     */
    private fun deleteDepartmentXML(dep: DepartmentDTO): String {
        val res = service.deleteDepartment(dep)
        Jaxb.departmentToXML(res)
        return ""
    }

    /**
     * Este metodo elimina un department y lo muestra como json
     * @author Daniel Rodriguez
     * @param dep DepartmentDTO
     * @return String json
     */
    private fun deleteDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.deleteDepartmentJSON:" +
                " Could not delete Department with id ${dep.id}")
    }
}