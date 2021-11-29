package services

import dto.RepositoryDTO
import mappers.RepositoryMapper
import model.Repository
import repositories.RepositoryRepository

class RepositoryService : BaseService<Repository, String, RepositoryRepository>(RepositoryRepository()) {
    val mapper = RepositoryMapper()

    fun getAllRepositories(): List<RepositoryDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getRepositoryById(id: String) : RepositoryDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun createRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(repo)))
    }

    fun updateRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(repo)))
    }

    fun deleteRepository(repo: RepositoryDTO) : RepositoryDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(repo)))
    }
}