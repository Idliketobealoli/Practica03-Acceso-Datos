package controllers

import com.google.gson.GsonBuilder
import dto.RepositoryDTO
import services.RepositoryService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al RepositoryService y mostrar lo que reciba como xml o json
 * @author Jaime Salcedo
 */
object RepositoryController {
    private val service = RepositoryService()
    /**
     * Este metodo muestra todos los repository como xml o json dependiendo del parámetro que le pases
     * @author Jaime Salcedo
     * @param returnMode String
     * @return String
     */
    fun findAllRepositories(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllRepositoriesXML()
            "JSON" -> findAllRepositoriesJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todos los repository y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     */
    private fun findAllRepositoriesXML() : String {
        val list = service.getAllRepositories()
        list.forEach { x -> Jaxb.repositoryToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todos los repository y las muestra como json
     * @author Jaime Salcedo
     * @return String json
     */
    private fun findAllRepositoriesJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllRepositories()) ?:
        throw SQLException("Error at RepositoryController.findAllRepositoriesJSON")
    }

    /**
     * Este metodo coge todos los repository dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Jaime Salcedo
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getRepositoryById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getRepositoryByIdXML(id)
            "JSON" -> getRepositoryByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todos los repository dependiendo de un id que le pasemos y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     * @param id String
     */
    private fun getRepositoryByIdXML(id: String): String {
        val x = service.getRepositoryById(id)
        Jaxb.repositoryToXML(x)
        return ""
    }

    /**
     * Este metodo coge todos los repository dependiendo de un id que le pasemos y las muestra como json
     * @author Jaime Salcedo
     * @param id String
     * @return String json
     */
    private fun getRepositoryByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getRepositoryById(id)) ?:
        throw SQLException("Error at RepositoryController.getRepositoryByIdJSON:" +
                " Repository with id $id not found.")
    }

    /**
     * Este metodo inserta un repository y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @param returnMode String
     * @return String
     */
    fun insertRepository(dto : RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertRepositoryXML(dto)
            "JSON" -> insertRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta un repository y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String xml
     */
    private fun insertRepositoryXML(dto: RepositoryDTO): String {
        val x = service.createRepository(dto)
        Jaxb.repositoryToXML(x)
        return ""
    }

    /**
     * Este metodo inserta un repository y lo muestra como json
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String json
     */
    private fun insertRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.insertRepositoryJSON:" +
                " Could not insert Repository with id ${dto.id}")

    }

    /**
     * Este metodo actualiza un repository y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @param returnMode String
     * @return String
     */
    fun updateRepository(dto: RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateRepositoryXML(dto)
            "JSON" -> updateRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza un repository y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String xml
     */
    private fun updateRepositoryXML(dto: RepositoryDTO): String {
        val x = service.updateRepository(dto)
        Jaxb.repositoryToXML(x)
        return ""
    }

    /**
     * Este metodo inserta un repository y lo muestra como json
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String json
     */
    private fun updateRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.updateRepositoryJSON:" +
                " Could not update Repository with id ${dto.id}")

    }

    /**
     * Este metodo elimina un repository y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @param returnMode String
     * @return String
     */
    fun deleteRepository(dto : RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteRepositoryXML(dto)
            "JSON" -> deleteRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina un repository y lo muestra como xml
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String xml
     */
    private fun deleteRepositoryXML(dto: RepositoryDTO): String {
        val x = service.deleteRepository(dto)
        Jaxb.repositoryToXML(x)
        return ""
    }

    /**
     * Este metodo elimina un repository y lo muestra como json
     * @author Jaime Salcedo
     * @param dto RepositoryDTO
     * @return String json
     */
    private fun deleteRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.deleteRepositoryJSON:" +
                " Could not delete Repository with id ${dto.id}")
    }
}