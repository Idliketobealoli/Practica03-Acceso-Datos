package controllers

import com.google.gson.GsonBuilder
import dto.ProgrammerDTO
import services.ProgrammerService
import java.sql.SQLException

/**
 * Esta clase se encarga de llamar al ProgrammerService y mostrar lo que reciba como xml o json
 * @author Jaime Salcedo
 */
object ProgrammerController {
    private val service = ProgrammerService()
    /**
     * Este metodo muestra todos los programmer como xml o json dependiendo del parámetro que le pases
     * @author Jaime Salcedo
     * @param returnMode String
     * @return String
     */
    fun findAllProgrammers(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllProgrammersXML()
            "JSON" -> findAllProgrammersJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    /**
     * Este metodo coge todos los programmer y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     */
    private fun findAllProgrammersXML(): String {
        val list = service.getAllProgrammers()
        list.forEach { x -> Jaxb.programmerToXML(x) }
        return ""
    }

    /**
     * Este metodo coge todos los programmer y las muestra como json
     * @author Jaime Salcedo
     * @return String json
     */
    private fun findAllProgrammersJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getAllProgrammers()) ?:
                throw SQLException("Error at ProgrammerController.findAllProgrammersJSON")
    }

    /**
     * Este metodo coge todos los programmer dependiendo de un id que le pasemos y las muestra en xml o json en
     * función del parámetro pasado
     * @author Jaime Salcedo
     * @return String
     * @param id String
     * @param returnMode String
     */
    fun getProgrammerById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getProgrammerByIdXML(id)
            "JSON" -> getProgrammerByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo coge todos los programmer dependiendo de un id que le pasemos y las muestra como xml
     * @author Jaime Salcedo
     * @return String xml
     * @param id String
     */
    private fun getProgrammerByIdXML(id: String): String {
        val res = service.getProgrammerById(id)
        Jaxb.programmerToXML(res)
        return ""
    }

    /**
     * Este metodo coge todos los programmer dependiendo de un id que le pasemos y las muestra como json
     * @author Jaime Salcedo
     * @param id String
     * @return String json
     */
    private fun getProgrammerByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getProgrammerById(id)) ?:
                throw SQLException("Error at ProgrammerController.getProgrammerByIdJSON:" +
                        " Programmer with id $id not found.")
    }

    /**
     * Este metodo inserta un programmer y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @param returnMode String
     * @return String
     */
    fun insertProgrammer(prog : ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertProgrammerXML(prog)
            "JSON" -> insertProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo inserta un programmer y lo muestra como xml
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String xml
     */
    private fun insertProgrammerXML(prog: ProgrammerDTO): String {
        val res = service.createProgrammer(prog)
        Jaxb.programmerToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un programmer y lo muestra como json
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String json
     */
    private fun insertProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createProgrammer(prog)) ?:
                throw SQLException("Error at ProgrammerController.insertProgrammerJSON:" +
                        " Could not insert Programmer with id ${prog.id}")

    }

    /**
     * Este metodo actualiza un programmer y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @param returnMode String
     * @return String
     */
    fun updateProgrammer(prog: ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateProgrammerXML(prog)
            "JSON" -> updateProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo actualiza un programmer y lo muestra como xml
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String xml
     */
    private fun updateProgrammerXML(prog: ProgrammerDTO): String {
        val res = service.updateProgrammer(prog)
        Jaxb.programmerToXML(res)
        return ""
    }

    /**
     * Este metodo inserta un programmer y lo muestra como json
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String json
     */
    private fun updateProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateProgrammer(prog)) ?:
        throw SQLException("Error at ProgrammerController.updateProgrammerJSON:" +
                " Could not update Programmer with id ${prog.id}")

    }

    /**
     * Este metodo elimina un programmer y lo muestra como xml o json, dependiendo del parámetro que le pasemos
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @param returnMode String
     * @return String
     */
    fun deleteProgrammer(prog : ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteProgrammerXML(prog)
            "JSON" -> deleteProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    /**
     * Este metodo elimina un programmer y lo muestra como xml
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String xml
     */
    private fun deleteProgrammerXML(prog: ProgrammerDTO): String {
        val res = service.deleteProgrammer(prog)
        Jaxb.programmerToXML(res)
        return ""
    }

    /**
     * Este metodo elimina un programmer y lo muestra como json
     * @author Jaime Salcedo
     * @param prog ProgrammerDTO
     * @return String json
     */
    private fun deleteProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteProgrammer(prog)) ?:
        throw SQLException("Error at ProgrammerController.deleteProgrammerJSON:" +
                " Could not delete Programmer with id ${prog.id}")

    }
}