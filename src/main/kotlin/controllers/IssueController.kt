package controllers

import com.google.gson.GsonBuilder
import dto.IssueDTO
import services.IssueService
import java.sql.SQLException

object IssueController {
    private val service = IssueService()
    fun findAllIssues(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllIssuesXML()
            "JSON" -> findAllIssueJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllIssuesXML() : String {
        return ""
    }

    private fun findAllIssueJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.findAll()) ?:
        throw SQLException("Error at IssueController.findAllIssuesJSON")
    }


    fun getIssueById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getIssueByIdXML(id)
            "JSON" -> getIssueByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getIssueByIdXML(id: String): String {
        return ""
    }

    private fun getIssueByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getIssueById(id)) ?:
        throw SQLException("Error at IssueController.getIssueByIdJSON:" +
                " Issue with id $id not found.")
    }


    fun insertIssue(dto : IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertIssueXML(dto)
            "JSON" -> insertIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertIssueXML(dto: IssueDTO): String {
        return ""
    }

    private fun insertIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createIssue(dto)) ?:
        throw SQLException("Error at IssueController.insertIssueJSON:" +
                " Could not insert Issue with id ${dto.id}")

    }


    fun updateIssue(dto: IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateIssueXML(dto)
            "JSON" -> updateIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateIssueXML(dto: IssueDTO): String {
        return ""
    }

    private fun updateIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateIssue(dto)) ?:
        throw SQLException("Error at IssueController.updateIssueJSON:" +
                " Could not update Issue with id ${dto.id}")

    }


    fun deleteIssue(dto : IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteIssueXML(dto)
            "JSON" -> deleteIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteIssueXML(dto: IssueDTO): String {
        return ""
    }

    private fun deleteIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteIssue(dto)) ?:
        throw SQLException("Error at IssueController.deleteIssueJSON:" +
                " Could not delete Issue with id ${dto.id}")
    }
}