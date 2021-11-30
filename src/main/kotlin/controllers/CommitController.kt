package controllers

import com.google.gson.GsonBuilder
import dto.CommitDTO
import services.CommitService
import java.sql.SQLException

object CommitController {
    private val service = CommitService()
    fun findAllCommits(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllCommitsXML()
            "JSON" -> findAllCommitsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllCommitsXML() : String {
        return ""
    }

    private fun findAllCommitsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.findAll()) ?:
        throw SQLException("Error at CommitController.findAllCommitsJSON")
    }


    fun getCommitById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getCommitByIdXML(id)
            "JSON" -> getCommitByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getCommitByIdXML(id: String): String {
        return ""
    }

    private fun getCommitByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getCommitById(id)) ?:
        throw SQLException("Error at CommitController.getCommitByIdJSON:" +
                " Commit with id $id not found.")
    }


    fun insertDepartment(dto : CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertCommitXML(dto)
            "JSON" -> insertCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertCommitXML(dto: CommitDTO): String {
        return ""
    }

    private fun insertCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createCommit(dto)) ?:
        throw SQLException("Error at CommitController.insertCommitJSON:" +
                " Could not insert Commit with id ${dto.id}")

    }


    fun updateDepartment(dto: CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateCommitXML(dto)
            "JSON" -> updateCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateCommitXML(dto: CommitDTO): String {
        return ""
    }

    private fun updateCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateCommit(dto)) ?:
        throw SQLException("Error at CommitController.updateCommitJSON:" +
                " Could not update Commit with id ${dto.id}")

    }


    fun deleteCommit(dto : CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteCommitXML(dto)
            "JSON" -> deleteCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteCommitXML(dto: CommitDTO): String {
        return ""
    }

    private fun deleteCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteCommit(dto)) ?:
        throw SQLException("Error at CommitController.deleteCommitJSON:" +
                " Could not delete Commit with id ${dto.id}")
    }
}