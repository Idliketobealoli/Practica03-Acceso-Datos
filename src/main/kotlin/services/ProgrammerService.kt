package services

import dto.ProgrammerDTO
import mappers.ProgrammerMapper
import model.Programmer
import repositories.ProgrammerRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Daniel Rodriguez
 * @see BaseService
 * @see ProgrammerRepository
 * @see ProgrammerMapper
 */
class ProgrammerService : BaseService<Programmer, String, ProgrammerRepository>(ProgrammerRepository()) {
    val mapper = ProgrammerMapper()

    /**
     * Llama a ProgrammerRepository para que busque todos los ProgrammerDTO en la base de datos,
     * los mapea y los devuelve como lista de ProgrammerDTO.
     * @author Daniel Rodriguez
     * @see ProgrammerMapper
     * @see ProgrammerRepository
     */
    fun getAllProgrammers() : List<ProgrammerDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a ProgrammerRepository para que busque el ProgrammerDTO en la base de datos cuyo id
     * coincida con el argumento introducido, lo mapea y lo devuelve como ProgrammerDTO.
     * @author Daniel Rodriguez
     * @see ProgrammerMapper
     * @see ProgrammerRepository
     */
    fun getProgrammerById(id: String) : ProgrammerDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a ProgrammerRepository para que inserte el ProgrammerDTO en la base de datos,
     * lo mapea y lo devuelve como ProgrammerDTO.
     * @author Daniel Rodriguez
     * @see ProgrammerMapper
     * @see ProgrammerRepository
     */
    fun createProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(prog)))
    }

    /**
     * Llama a ProgrammerRepository para que actualice el ProgrammerDTO en la base de datos,
     * lo mapea y lo devuelve como ProgrammerDTO.
     * @author Daniel Rodriguez
     * @see ProgrammerMapper
     * @see ProgrammerRepository
     */
    fun updateProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(prog)))
    }

    /**
     * Llama a ProgrammerRepository para que borre el ProgrammerDTO en la base de datos,
     * lo mapea y lo devuelve como ProgrammerDTO.
     * @author Daniel Rodriguez
     * @see ProgrammerMapper
     * @see ProgrammerRepository
     */
    fun deleteProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(prog)))
    }
}