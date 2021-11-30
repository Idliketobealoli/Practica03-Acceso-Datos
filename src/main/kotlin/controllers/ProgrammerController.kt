package controllers

import com.google.gson.GsonBuilder
import dto.ProgrammerDTO
import services.ProgrammerService
import java.sql.SQLException

object ProgrammerController {
    private val service = ProgrammerService()
    fun findAllProgrammers(returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> findAllProgrammersXML()
            "JSON" -> findAllProgrammersJSON()
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }

    }

    private fun findAllProgrammersXML(): String {
        return ""
    }

    private fun findAllProgrammersJSON() : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.findAll()) ?:
                throw SQLException("Error at ProgrammerController.findAllProgrammersJSON")
    }


    fun getProgrammerById(id : String, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> getProgrammerByIdXML(id)
            "JSON" -> getProgrammerByIdJSON(id)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun getProgrammerByIdXML(id: String): String {
        return ""
    }

    private fun getProgrammerByIdJSON(id : String) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.getProgrammerById(id)) ?:
                throw SQLException("Error at ProgrammerController.getProgrammerByIdJSON:" +
                        " Programmer with id $id not found.")
    }


    fun insertProgrammer(prog : ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> insertProgrammerXML(prog)
            "JSON" -> insertProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun insertProgrammerXML(prog: ProgrammerDTO): String {
        return ""
    }

    private fun insertProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.createProgrammer(prog)) ?:
                throw SQLException("Error at ProgrammerController.insertProgrammerJSON:" +
                        " Could not insert Programmer with id ${prog.id}")

    }


    fun updateProgrammer(prog: ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> updateProgrammerXML(prog)
            "JSON" -> updateProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun updateProgrammerXML(prog: ProgrammerDTO): String {
        return ""
    }

    private fun updateProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.updateProgrammer(prog)) ?:
        throw SQLException("Error at ProgrammerController.updateProgrammerJSON:" +
                " Could not update Programmer with id ${prog.id}")

    }


    fun deleteProgrammer(prog : ProgrammerDTO, returnMode : String) : String {
        return when (returnMode.toUpperCase()) {
            "XML" -> deleteProgrammerXML(prog)
            "JSON" -> deleteProgrammerJSON(prog)
            else -> throw Exception("Invalid parameter. Admitted parameters: XML , JSON")
        }
    }

    private fun deleteProgrammerXML(prog: ProgrammerDTO): String {
        return ""
    }

    private fun deleteProgrammerJSON(prog : ProgrammerDTO) : String {
        return GsonBuilder().setPrettyPrinting().create()
                .toJson(service.deleteProgrammer(prog)) ?:
        throw SQLException("Error at ProgrammerController.deleteProgrammerJSON:" +
                " Could not delete Programmer with id ${prog.id}")

    }
}