package controllers

import com.google.gson.GsonBuilder
import dto.CommitDTO
import services.CommitService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al CommitService y mostrar lo que reciba como xml o json
 * @author Daniel Rodriguez
 */
object CommitController {
    private val service = CommitService()
    /**
     * Este metodo muestra todos los commits como xml o json dependiendo del parámetro que le pases
     * @author Daniel Rodriguez
     * @param returnMode String
     * @return String
     */
    fun findAllCommits(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllCommitsXML()
            "JSON" -> findAllCommitsJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todos los commit y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     */
    private fun findAllCommitsXML() : String {
        val list = service.findAllCommits()
        list.forEach { x -> Jaxb.commitToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todos los commits y las muestra como json
     * @author Daniel Rodriguez
     * @return String json
     */
    private fun findAllCommitsJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.findAllCommits()) ?:
        throw SQLException("Error at CommitController.findAllCommitsJSON")
    }

    /**
     * Este metodo coge todos los commits dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Daniel Rodriguez
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getCommitById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getCommitByIdXML(id)
            "JSON" -> getCommitByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todos los commits dependiendo de un id que le pasemos y las muestra como xml
     * @author Daniel Rodriguez
     * @return String xml
     * @param id String
     */
    private fun getCommitByIdXML(id: String): String {
        val res = service.getCommitById(id)
        Jaxb.commitToXML(res)
        return ""
    }

    /**
     * Este metodo coge todos los commits dependiendo de un id que le pasemos y las muestra como json
     * @author Daniel Rodriguez
     * @param id String
     * @return String json
     */
    private fun getCommitByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getCommitById(id)) ?:
        throw SQLException("Error at CommitController.getCommitByIdJSON:" +
                " Commit with id $id not found.")
    }

    /**
     * Este metodo inserta un commit y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @param returnMode String
     * @return String
     */
    fun insertCommit(dto : CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertCommitXML(dto)
            "JSON" -> insertCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta un commit y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String xml
     */
    private fun insertCommitXML(dto: CommitDTO): String {
        val res = service.createCommit(dto)
        Jaxb.commitToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un commit y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String json
     */
    private fun insertCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createCommit(dto)) ?:
        throw SQLException("Error at CommitController.insertCommitJSON:" +
                " Could not insert Commit with id ${dto.id}")

    }

    /**
     * Este metodo actualiza un commit y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @param returnMode String
     * @return String
     */
    fun updateCommit(dto: CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateCommitXML(dto)
            "JSON" -> updateCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza un commit y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String xml
     */
    private fun updateCommitXML(dto: CommitDTO): String {
        val res = service.updateCommit(dto)
        Jaxb.commitToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un commit y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String json
     */
    private fun updateCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateCommit(dto)) ?:
        throw SQLException("Error at CommitController.updateCommitJSON:" +
                " Could not update Commit with id ${dto.id}")

    }

    /**
     * Este metodo elimina un commit y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @param returnMode String
     * @return String
     */
    fun deleteCommit(dto : CommitDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteCommitXML(dto)
            "JSON" -> deleteCommitJSON(dto)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina un commit y lo muestra como xml
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String xml
     */
    private fun deleteCommitXML(dto: CommitDTO): String {
        val res = service.deleteCommit(dto)
        Jaxb.commitToXML(res)
        return ""
    }

    /**
     * Este metodo elimina un commit y lo muestra como json
     * @author Daniel Rodriguez
     * @param dto CommitDTO
     * @return String json
     */
    private fun deleteCommitJSON(dto : CommitDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteCommit(dto)) ?:
        throw SQLException("Error at CommitController.deleteCommitJSON:" +
                " Could not delete Commit with id ${dto.id}")
    }
}