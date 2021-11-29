package services

import dto.IssueDTO
import mappers.IssueMapper
import model.Issue
import repositories.IssueRepository

class IssueService : BaseService<Issue, String, IssueRepository>(IssueRepository()) {
    val mapper = IssueMapper()

    fun getAllIssues() : List<IssueDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getIssueById(id: String) : IssueDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun createIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(issue)))
    }

    fun updateIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(issue)))
    }

    fun deleteIssue(issue : IssueDTO) : IssueDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(issue)))
    }
}