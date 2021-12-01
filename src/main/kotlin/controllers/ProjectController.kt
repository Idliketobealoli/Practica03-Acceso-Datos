package controllers

import com.google.gson.GsonBuilder
import dto.ProjectDTO
import services.ProjectService
import java.sql.SQLException

object ProjectController {
    private val service = ProjectService()
    fun findAllProjects(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllProjectsXML()
            "JSON" -> findAllProjectsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllProjectsXML() : String {
        val list = service.getAllProjects()
        list.forEach { x -> Jaxb.projectToXML(x) }
        return ""
    }

    private fun findAllProjectsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllProjects()) ?:
        throw SQLException("Error at ProjectController.findAllProjectsJSON")
    }


    fun getProjectById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getProjectByIdXML(id)
            "JSON" -> getProjectByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getProjectByIdXML(id: String): String {
        val res = service.getProjectById(id)
        Jaxb.projectToXML(res)
        return ""
    }

    private fun getProjectByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getProjectById(id)) ?:
        throw SQLException("Error at ProjectController.getProjectByIdJSON:" +
                " Project with id $id not found.")
    }


    fun insertProject(dto : ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertProjectXML(dto)
            "JSON" -> insertProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertProjectXML(dto: ProjectDTO): String {
        val res = service.createProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    private fun insertProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createProject(dto)) ?:
        throw SQLException("Error at ProjectController.insertProjectJSON:" +
                " Could not insert Project with id ${dto.id}")

    }


    fun updateProject(dto: ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateProjectXML(dto)
            "JSON" -> updateProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateProjectXML(dto: ProjectDTO): String {
        val res = service.updateProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    private fun updateProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateProject(dto)) ?:
        throw SQLException("Error at ProjectController.updateProjectJSON:" +
                " Could not update Project with id ${dto.id}")

    }


    fun deleteProject(dto : ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteProjectXML(dto)
            "JSON" -> deleteProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteProjectXML(dto: ProjectDTO): String {
        val res = service.deleteProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    private fun deleteProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteProject(dto)) ?:
        throw SQLException("Error at ProjectController.deleteProjectJSON:" +
                " Could not delete Project with id ${dto.id}")
    }
}