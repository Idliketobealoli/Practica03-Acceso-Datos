package services

import dto.ProgrammerDTO
import mappers.ProgrammerMapper
import model.Programmer
import repositories.ProgrammerRepository

class ProgrammerService : BaseService<Programmer, String, ProgrammerRepository>(ProgrammerRepository()) {
    val mapper = ProgrammerMapper()

    fun getAllProgrammers() : List<ProgrammerDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getProgrammerById(id: String) : ProgrammerDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun createProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(prog)))
    }

    fun updateProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(prog)))
    }

    fun deleteProgrammer(prog : ProgrammerDTO) : ProgrammerDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(prog)))
    }
}