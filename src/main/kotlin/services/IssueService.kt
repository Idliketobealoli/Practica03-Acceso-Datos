package services

import dto.IssueDTO
import mappers.IssueMapper
import model.Issue
import repositories.IssueRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Jaime Salcedo
 * @see BaseService
 * @see IssueRepository
 * @see IssueMapper
 */
class IssueService : BaseService<Issue, String, IssueRepository>(IssueRepository()) {
    val mapper = IssueMapper()

    /**
     * Llama a IssueRepository para que busque todos los IssueDTO en la base de datos,
     * los mapea y los devuelve como lista de IssueDTO.
     * @author Jaime Salcedo
     * @see IssueMapper
     * @see IssueRepository
     */
    fun getAllIssues() : List<IssueDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a IssueRepository para que busque el IssueDTO cuyo id corresponda con el string
     * pasado como argumento en la base de datos, lo mapea y lo devuelve como IssueDTO.
     * @author Jaime Salcedo
     * @see IssueMapper
     * @see IssueRepository
     */
    fun getIssueById(id: String) : IssueDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a IssueRepository para que inserte el IssueDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como IssueDTO.
     * @author Jaime Salcedo
     * @see IssueMapper
     * @see IssueRepository
     */
    fun createIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(issue)))
    }

    /**
     * Llama a IssueRepository para que actualice el IssueDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como IssueDTO.
     * @author Jaime Salcedo
     * @see IssueMapper
     * @see IssueRepository
     */
    fun updateIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(issue)))
    }

    /**
     * Llama a IssueRepository para que borre el IssueDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como IssueDTO.
     * @author Jaime Salcedo
     * @see IssueMapper
     * @see IssueRepository
     */
    fun deleteIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(issue)))
    }
}