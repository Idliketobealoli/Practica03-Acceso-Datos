package mappers

import dto.ProjectDTO
import model.Project
import repositories.DepartmentRepository
import repositories.ProgrammerRepository
import repositories.RepositoryRepository
import utils.Utils

/**
 * Clase encargada de mapear un objeto Project pasandolo a DTO o a la inversa.
 * @author Jaime Salcedo
 * @see BaseMapper
 */
class ProjectMapper : BaseMapper<Project, ProjectDTO>() {

    /**
     * Coge un ProjectDTO y lo convierte en un Project
     * @author Jaime Salcedo
     * @param item ProjectDTO
     * @return Project
     */
    override fun fromDTO(item: ProjectDTO): Project {
        Utils().makeSureTheseAreIds(item.id, item.department.id,
                item.projectManager.id, item.repository.id)
        Utils().makeSureThisGuyIsProjectManager(item.projectManager, item.id)
        return Project(
                item.id, item.department.id, item.projectManager.id,
                item.name, item.budget,
                Utils().matchesDate(item.startDate),
                Utils().matchesDateAcceptingNull(item.endDate),
                Utils().getTechnologiesAsString(item.technologies),
                item.repository.id, Utils().booleanToInt(item.isFinished),
                Utils().getProgrammersIDS(item.programmers)
        )
    }

    /**
     * Coge un Project y lo convierte en ProjectDTO
     * @author Jaime Salcedo
     * @param item Project
     * @return ProjectDTO
     */
    override fun toDTO(item: Project): ProjectDTO {
        Utils().makeSureTheseAreIds(item.id, item.department_id,
                item.projectManager_id, item.repository_id)
        Utils().makeSureThisGuyIsProjectManager(
                ProgrammerRepository().getById(item.projectManager_id), item.id)
        return ProjectDTO(
                item.id, DepartmentRepository().getById(item.department_id),
                ProgrammerRepository().getById(item.projectManager_id),
                item.name, item.budget, Utils().matchesDate(item.startDate),
                Utils().matchesDateAcceptingNull(item.endDate),
                Utils().getTechnologies(item.technologies),
                RepositoryRepository().getById(item.repository_id),
                Utils().intToBoolean(item.isFinished),
                Utils().getProgrammers(item.programmers_ids)
        )
    }
}