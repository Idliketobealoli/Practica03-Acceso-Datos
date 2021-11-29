package services

import dto.CommitDTO
import mappers.CommitMapper
import model.Commit
import repositories.CommitRepository

class CommitService : BaseService<Commit, String, CommitRepository>(CommitRepository()) {
    val mapper = CommitMapper()

    fun findAllCommits() : List<CommitDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getCommitById(id: String) : CommitDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun insertCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(commit)))
    }

    fun updateCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(commit)))
    }

    fun deleteCommit(commit: CommitDTO) : CommitDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(commit)))
    }
}