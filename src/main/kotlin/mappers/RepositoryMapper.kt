package mappers

import dto.RepositoryDTO
import model.Repository
import repositories.ProjectRepository
import utils.Utils

/**
 * Clase encargada de mapear un objeto Repository pasandolo a DTO o a la inversa.
 * @author Jaime Salcedo
 * @see BaseMapper
 */
class RepositoryMapper : BaseMapper<Repository, RepositoryDTO>() {

    /**
     * Coge un RepositoryDTO y lo convierte en un Repository
     * @author Jaime Salcedo
     * @param item RepositoryDTO
     * @return Repository
     */
    override fun fromDTO(item: RepositoryDTO): Repository {
        Utils().makeSureTheseAreIds(item.id, item.project.id)
        return Repository(
                item.id, item.name, Utils().matchesDate(item.creationDate),
                item.project.id, Utils().getCommitsIDS(item.commits),
                Utils().getIssuesIDS(item.issues)
        )
    }

    /**
     * Coge un Repository y lo convierte en RepositoryDTO
     * @author Jaime Salcedo
     * @param item Repository
     * @return RepositoryDTO
     */
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