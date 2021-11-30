package controllers

import com.google.gson.GsonBuilder
import dto.RepositoryDTO
import services.RepositoryService
import java.sql.SQLException

object RepositoryController {
    private val service = RepositoryService()
    fun findAllRepositories(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllRepositoriesXML()
            "JSON" -> findAllRepositoriesJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllRepositoriesXML() : String {
        return ""
    }

    private fun findAllRepositoriesJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllRepositories()) ?:
        throw SQLException("Error at RepositoryController.findAllRepositoriesJSON")
    }


    fun getRepositoryById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getRepositoryByIdXML(id)
            "JSON" -> getRepositoryByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getRepositoryByIdXML(id: String): String {
        return ""
    }

    private fun getRepositoryByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getRepositoryById(id)) ?:
        throw SQLException("Error at RepositoryController.getRepositoryByIdJSON:" +
                " Repository with id $id not found.")
    }


    fun insertRepository(dto : RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertRepositoryXML(dto)
            "JSON" -> insertRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertRepositoryXML(dto: RepositoryDTO): String {
        return ""
    }

    private fun insertRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.insertRepositoryJSON:" +
                " Could not insert Repository with id ${dto.id}")

    }


    fun updateRepository(dto: RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateRepositoryXML(dto)
            "JSON" -> updateRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateRepositoryXML(dto: RepositoryDTO): String {
        return ""
    }

    private fun updateRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.updateRepositoryJSON:" +
                " Could not update Repository with id ${dto.id}")

    }


    fun deleteRepository(dto : RepositoryDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteRepositoryXML(dto)
            "JSON" -> deleteRepositoryJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteRepositoryXML(dto: RepositoryDTO): String {
        return ""
    }

    private fun deleteRepositoryJSON(dto : RepositoryDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteRepository(dto)) ?:
        throw SQLException("Error at RepositoryController.deleteRepositoryJSON:" +
                " Could not delete Repository with id ${dto.id}")
    }
}