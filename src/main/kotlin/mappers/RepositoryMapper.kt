package mappers

import dto.RepositoryDTO
import model.Repository
import repositories.ProjectRepository
import utils.Utils

class RepositoryMapper : BaseMapper<Repository, RepositoryDTO>() {
    override fun fromDTO(item: RepositoryDTO): Repository {
        Utils().makeSureTheseAreIds(item.id, item.project.id)
        return Repository(
                item.id, item.name, Utils().matchesDate(item.creationDate),
                item.project.id, Utils().getCommitsIDS(item.commits),
                Utils().getIssuesIDS(item.issues)
        )
    }

    override fun toDTO(item: Repository): RepositoryDTO {
        Utils().makeSureTheseAreIds(item.id, item.project_id)
        return RepositoryDTO(
                item.id, item.name, Utils().matchesDate(item.creationDate),
                ProjectRepository().getById(item.project_id),
                Utils().getCommits(item.commits_ids),
                Utils().getIssues(item.issues_ids)
        )
    }
}