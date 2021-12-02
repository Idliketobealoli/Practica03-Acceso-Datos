package services

import dto.CommitDTO
import mappers.CommitMapper
import model.Commit
import repositories.CommitRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Jaime Salcedo
 * @see BaseService
 * @see CommitRepository
 * @see CommitMapper
 */
class CommitService : BaseService<Commit, String, CommitRepository>(CommitRepository()) {
    val mapper = CommitMapper()

    /**
     * Llama a CommitRepository para que busque todos los commits, los mapea y devuelve una lista de CommitDTO.
     * @author Jaime Salcedo
     * @see CommitMapper
     * @see CommitRepository
     */
    fun findAllCommits() : List<CommitDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a CommitRepository para que busque el commit cuyo id corresponda con el argumento pasado,
     * lo mapea y lo devuelve como CommitDTO.
     * @author Jaime Salcedo
     * @see CommitMapper
     * @see CommitRepository
     */
    fun getCommitById(id: String) : CommitDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a CommitRepository para que inserte el commitDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como CommitDTO.
     * @author Jaime Salcedo
     * @see CommitMapper
     * @see CommitRepository
     */
    fun createCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(commit)))
    }

    /**
     * Llama a CommitRepository para que modifique el commitDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como CommitDTO.
     * @author Jaime Salcedo
     * @see CommitMapper
     * @see CommitRepository
     */
    fun updateCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(commit)))
    }

    /**
     * Llama a CommitRepository para que borre el commitDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como CommitDTO.
     * @author Jaime Salcedo
     * @see CommitMapper
     * @see CommitRepository
     */
    fun deleteCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(commit)))
    }
}