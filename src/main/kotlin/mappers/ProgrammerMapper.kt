package mappers

import dto.ProgrammerDTO
import model.Programmer
import repositories.DepartmentRepository
import utils.Utils

class ProgrammerMapper : BaseMapper<Programmer, ProgrammerDTO>() {
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