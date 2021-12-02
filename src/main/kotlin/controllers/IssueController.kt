package controllers

import com.google.gson.GsonBuilder
import dto.IssueDTO
import services.IssueService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al IssueService y mostrar lo que reciba como xml o json
 * @author Daniel Rodriguez
 */
object IssueController {
    private val service = IssueService()
    /**
     * Este metodo muestra todas las issues como xml o json dependiendo del parámetro que le pases
     * @author Daniel Rodriguez
     * @param returnMode String
     * @return String
     */
    fun findAllIssues(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllIssuesXML()
            "JSON" -> findAllIssueJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todas las issues y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     */
    private fun findAllIssuesXML() : String {
        val list = service.getAllIssues()
        list.forEach { x -> Jaxb.issueToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todas las issues y las muestra como json
     * @author Daniel Rodriguez
     * @return String json
     */
    private fun findAllIssueJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllIssues()) ?:
        throw SQLException("Error at IssueController.findAllIssuesJSON")
    }

    /**
     * Este metodo coge todas las issues dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Daniel Rodriguez
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getIssueById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getIssueByIdXML(id)
            "JSON" -> getIssueByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todas las issues dependiendo de un id que le pasemos y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     * @param id String
     */
    private fun getIssueByIdXML(id: String): String {
        val x = service.getIssueById(id)
        Jaxb.issueToXML(x)
        return ""
    }

    /**
     * Este metodo coge todas las issues dependiendo de un id que le pasemos y las muestra como json
     * @author Daniel Rodriguez
     * @param id String
     * @return String json
     */
    private fun getIssueByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getIssueById(id)) ?:
        throw SQLException("Error at IssueController.getIssueByIdJSON:" +
                " Issue with id $id not found.")
    }

    /**
     * Este metodo inserta una issue y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @param returnMode String
     * @return String
     */
    fun insertIssue(dto : IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertIssueXML(dto)
            "JSON" -> insertIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta una issue y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String xml
     */
    private fun insertIssueXML(dto: IssueDTO): String {
        val x = service.createIssue(dto)
        Jaxb.issueToXML(x)
        return ""
    }

    /**
     * Este metodo inserta una issue y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String json
     */
    private fun insertIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createIssue(dto)) ?:
        throw SQLException("Error at IssueController.insertIssueJSON:" +
                " Could not insert Issue with id ${dto.id}")

    }

    /**
     * Este metodo actualiza una issue y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @param returnMode String
     * @return String
     */
    fun updateIssue(dto: IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateIssueXML(dto)
            "JSON" -> updateIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza una issue y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String xml
     */
    private fun updateIssueXML(dto: IssueDTO): String {
        val x = service.updateIssue(dto)
        Jaxb.issueToXML(x)
        return ""
    }

    /**
     * Este metodo inserta una issue y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String json
     */
    private fun updateIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateIssue(dto)) ?:
        throw SQLException("Error at IssueController.updateIssueJSON:" +
                " Could not update Issue with id ${dto.id}")

    }

    /**
     * Este metodo elimina una issue y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @param returnMode String
     * @return String
     */
    fun deleteIssue(dto : IssueDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteIssueXML(dto)
            "JSON" -> deleteIssueJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina una issue y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String xml
     */
    private fun deleteIssueXML(dto: IssueDTO): String {
        val x = service.deleteIssue(dto)
        Jaxb.issueToXML(x)
        return ""
    }

    /**
     * Este metodo elimina una issue y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto IssueDTO
     * @return String json
     */
    private fun deleteIssueJSON(dto : IssueDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteIssue(dto)) ?:
        throw SQLException("Error at IssueController.deleteIssueJSON:" +
                " Could not delete Issue with id ${dto.id}")
    }
}