package controllers

import com.google.gson.GsonBuilder
import dto.ProjectDTO
import services.ProjectService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al ProjectService y mostrar lo que reciba como xml o json
 * @author Jaime Salcedo
 */
object ProjectController {
    private val service = ProjectService()
    /**
     * Este metodo muestra todos los project como xml o json dependiendo del parámetro que le pases
     * @author Jaime Salcedo
     * @param returnMode String
     * @return String
     */
    fun findAllProjects(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllProjectsXML()
            "JSON" -> findAllProjectsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todos los project y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     */
    private fun findAllProjectsXML() : String {
        val list = service.getAllProjects()
        list.forEach { x -> Jaxb.projectToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todos los project y las muestra como json
     * @author Jaime Salcedo
     * @return String json
     */
    private fun findAllProjectsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllProjects()) ?:
        throw SQLException("Error at ProjectController.findAllProjectsJSON")
    }

    /**
     * Este metodo coge todos los project dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Jaime Salcedo
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getProjectById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getProjectByIdXML(id)
            "JSON" -> getProjectByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todos los project dependiendo de un id que le pasemos y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     * @param id String
     */
    private fun getProjectByIdXML(id: String): String {
        val res = service.getProjectById(id)
        Jaxb.projectToXML(res)
        return ""
    }

    /**
     * Este metodo coge todos los project dependiendo de un id que le pasemos y las muestra como json
     * @author Jaime Salcedo
     * @param id String
     * @return String json
     */
    private fun getProjectByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getProjectById(id)) ?:
        throw SQLException("Error at ProjectController.getProjectByIdJSON:" +
                " Project with id $id not found.")
    }

    /**
     * Este metodo inserta un project y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @param returnMode String
     * @return String
     */
    fun insertProject(dto : ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertProjectXML(dto)
            "JSON" -> insertProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta un project y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String xml
     */
    private fun insertProjectXML(dto: ProjectDTO): String {
        val res = service.createProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un project y lo muestra como json
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String json
     */
    private fun insertProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createProject(dto)) ?:
        throw SQLException("Error at ProjectController.insertProjectJSON:" +
                " Could not insert Project with id ${dto.id}")

    }

    /**
     * Este metodo actualiza un project y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @param returnMode String
     * @return String
     */
    fun updateProject(dto: ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateProjectXML(dto)
            "JSON" -> updateProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza un project y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String xml
     */
    private fun updateProjectXML(dto: ProjectDTO): String {
        val res = service.updateProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un project y lo muestra como json
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String json
     */
    private fun updateProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateProject(dto)) ?:
        throw SQLException("Error at ProjectController.updateProjectJSON:" +
                " Could not update Project with id ${dto.id}")

    }

    /**
     * Este metodo elimina un project y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @param returnMode String
     * @return String
     */
    fun deleteProject(dto : ProjectDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteProjectXML(dto)
            "JSON" -> deleteProjectJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina un project y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String xml
     */
    private fun deleteProjectXML(dto: ProjectDTO): String {
        val res = service.deleteProject(dto)
        Jaxb.projectToXML(res)
        return ""
    }

    /**
     * Este metodo elimina un project y lo muestra como json
     * @author Jaime Salcedo
     * @param dto ProjectDTO
     * @return String json
     */
    private fun deleteProjectJSON(dto : ProjectDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteProject(dto)) ?:
        throw SQLException("Error at ProjectController.deleteProjectJSON:" +
                " Could not delete Project with id ${dto.id}")
    }
}