package services

import dto.RepositoryDTO
import mappers.RepositoryMapper
import model.Repository
import repositories.RepositoryRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Daniel Rodriguez
 * @see BaseService
 * @see RepositoryRepository
 * @see RepositoryMapper
 */
class RepositoryService : BaseService<Repository, String, RepositoryRepository>(RepositoryRepository()) {
    val mapper = RepositoryMapper()

    /**
     * Llama a RepositoryRepository para que busque todos los RepositoryDTO en la base de datos,
     * los mapea y los devuelve como lista de RepositoryDTO.
     * @author Daniel Rodriguez
     * @see RepositoryMapper
     * @see RepositoryRepository
     */
    fun getAllRepositories(): List<RepositoryDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a RepositoryRepository para que busque el RepositoryDTO cuyo id sea igual al introducido
     * por parametro en la base de datos, lo mapea y lo devuelve como RepositoryDTO.
     * @author Daniel Rodriguez
     * @see RepositoryMapper
     * @see RepositoryRepository
     */
    fun getRepositoryById(id: String) : RepositoryDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a RepositoryRepository para que inserte el RepositoryDTO en la base de datos,
     * lo mapea y lo devuelve como RepositoryDTO.
     * @author Daniel Rodriguez
     * @see RepositoryMapper
     * @see RepositoryRepository
     */
    fun createRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(repo)))
    }

    /**
     * Llama a RepositoryRepository para que actualice el RepositoryDTO en la base de datos,
     * lo mapea y lo devuelve como RepositoryDTO.
     * @author Daniel Rodriguez
     * @see RepositoryMapper
     * @see RepositoryRepository
     */
    fun updateRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(repo)))
    }

    /**
     * Llama a RepositoryRepository para que borre el RepositoryDTO en la base de datos,
     * lo mapea y lo devuelve como RepositoryDTO.
     * @author Daniel Rodriguez
     * @see RepositoryMapper
     * @see RepositoryRepository
     */
    fun deleteRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(repo)))
    }
}