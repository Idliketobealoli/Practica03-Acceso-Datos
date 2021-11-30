package controllers

import com.google.gson.GsonBuilder
import dto.DepartmentDTO
import services.DepartmentService
import java.sql.SQLException

object DepartmentController {
    private val service = DepartmentService()
    fun findAllDepartments(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllDepartmentsXML()
            "JSON" -> findAllDepartmentsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllDepartmentsXML() : String {
        return ""
    }

    private fun findAllDepartmentsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.findAll()) ?:
        throw SQLException("Error at DepartmentController.findAllDepartmentsJSON")
    }


    fun getDepartmentById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getDepartmentByIdXML(id)
            "JSON" -> getDepartmentByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getDepartmentByIdXML(id: String): String {
        return ""
    }

    private fun getDepartmentByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getDepartmentById(id)) ?:
        throw SQLException("Error at DepartmentController.getDepartmentByIdJSON:" +
                " Department with id $id not found.")
    }


    fun insertDepartment(dep : DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertDepartmentXML(dep)
            "JSON" -> insertDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertDepartmentXML(dep: DepartmentDTO): String {
        return ""
    }

    private fun insertDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.insertDepartmentJSON:" +
                " Could not insert Department with id ${dep.id}")

    }


    fun updateDepartment(dep: DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateDepartmentXML(dep)
            "JSON" -> updateDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateDepartmentXML(dep: DepartmentDTO): String {
        return ""
    }

    private fun updateDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.updateDepartmentJSON:" +
                " Could not update Department with id ${dep.id}")

    }


    fun deleteDepartment(dep : DepartmentDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteDepartmentXML(dep)
            "JSON" -> deleteDepartmentJSON(dep)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteDepartmentXML(dep: DepartmentDTO): String {
        return ""
    }

    private fun deleteDepartmentJSON(dep : DepartmentDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteDepartment(dep)) ?:
        throw SQLException("Error at DepartmentController.deleteDepartmentJSON:" +
                " Could not delete Department with id ${dep.id}")
    }
}