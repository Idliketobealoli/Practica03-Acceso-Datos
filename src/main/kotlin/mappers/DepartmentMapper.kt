package mappers

import dto.DepartmentDTO
import model.Department
import repositories.ProgrammerRepository
import utils.Utils

class DepartmentMapper : BaseMapper<Department, DepartmentDTO>() {
    override fun fromDTO(item: DepartmentDTO): Department {
        Utils().makeSureTheseAreIds(item.id, item.boss.id)
        return Department(
                item.id, item.name, item.boss.id,
                item.budget,
                Utils().getProjectsIDS(item.finishedProjects),
                Utils().getProjectsIDS(item.developingProjects),
                item.anualBudget,
                Utils().getProgrammersIDS(item.bossHistory)
        )
    }

    override fun toDTO(item: Department): DepartmentDTO {
        Utils().makeSureTheseAreIds(item.id, item.boss_id)
        return DepartmentDTO(
                item.id, item.name,
                ProgrammerRepository().getById(item.boss_id),
                item.budget,
                Utils().getProjects(item.finishedProjects_ids),
                Utils().getProjects(item.developingProjects_ids),
                item.anualBudget,
                Utils().getProgrammers(item.bossHistory_ids)
        )
    }

}