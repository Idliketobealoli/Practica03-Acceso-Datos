package mappers

import dto.ProgrammerDTO
import model.Programmer
import repositories.DepartmentRepository
import utils.Utils

/**
 * Clase encargada de mapear un objeto Programmer pasandolo a DTO o a la inversa.
 * @author Jaime Salcedo
 * @see BaseMapper
 */
class ProgrammerMapper : BaseMapper<Programmer, ProgrammerDTO>() {

    /**
     * Coge un ProgrammerDTO y lo convierte en un Programmer
     * @author Jaime Salcedo
     * @param item ProgrammerDTO
     * @return Programmer
     */
    override fun fromDTO (item : ProgrammerDTO) : Programmer {
        Utils().makeSureBooleansAreCorrect(item)
        Utils().makeSureTheseAreIds(item.id, item.department.id)
        return Programmer(
                item.id, item.name, Utils().matchesDate(item.registerDate),
                item.department.id,
                Utils().getProjectsIDS(item.activeProjects),
                Utils().getCommitsIDS(item.commits),
                Utils().getIssuesIDS(item.issues),
                Utils().getTechnologiesAsString(item.technologies), item.salary,
                Utils().booleanToInt(item.isDepBoss),
                Utils().booleanToInt(item.isProjectManager),
                Utils().booleanToInt(item.isActive),
        )
    }

    /**
     * Coge un Programmer y lo convierte en ProgrammerDTO
     * @author Jaime Salcedo
     * @param item Programmer
     * @return ProgrammerDTO
     */
    override fun toDTO (item : Programmer) : ProgrammerDTO {
        Utils().makeSureBooleansAreCorrect(item)
        Utils().makeSureTheseAreIds(item.id, item.department_id)
        return ProgrammerDTO(
                item.id, item.name, Utils().matchesDate(item.registerDate),
                DepartmentRepository().getById(item.department_id),
                Utils().getProjects(item.activeProjects_ids),
                Utils().getCommits(item.commits_ids),
                Utils().getIssues(item.issues_ids),
                Utils().getTechnologies(item.technologies), item.salary,
                Utils().intToBoolean(item.isDepBoss),
                Utils().intToBoolean(item.isProjectManager),
                Utils().intToBoolean(item.isActive),
        )
    }
}