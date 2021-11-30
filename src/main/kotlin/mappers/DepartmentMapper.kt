package mappers

import dto.DepartmentDTO
import model.Department
import model.Project
import repositories.ProgrammerRepository
import utils.Utils
import java.util.ArrayList

class DepartmentMapper : BaseMapper<Department, DepartmentDTO>() {
    override fun fromDTO(item: DepartmentDTO): Department {
        Utils().makeSureTheseAreIds(item.id, item.boss.id)
        Utils().makeSureThisGuyIsDepBoss(item.boss);
        var programmers = ""+Utils().getProgrammersIDS(item.bossHistory)
        if (programmers.isEmpty() || !(programmers.contains(item.boss.id))) {
            programmers+=item.boss.id
        }
        var projectsIDS = ""
        val str1 = Utils().getProjectsIDS(item.finishedProjects)
        val str2 = Utils().getProjectsIDS(item.developingProjects)
        if (str1.isNotEmpty()) projectsIDS += str1
        if (str2.isNotEmpty()) projectsIDS += str2
        val projects = Utils().getProjects(projectsIDS)
        val finishedProjects = projects?.filter {
            project:Project -> Utils().intToBoolean(project.isFinished) }
        val developingProjects = projects?.filter {
            project:Project -> !Utils().intToBoolean(project.isFinished) }
        val finishedProjectsIds = Utils().getProjectsIDS(finishedProjects)
        val developingProjectsIds = Utils().getProjectsIDS(developingProjects)
        var anualBudget = item.budget
        developingProjects?.forEach { anualBudget += it.budget}

        return Department(
                item.id, item.name, item.boss.id, item.budget,
                finishedProjectsIds, developingProjectsIds,
                anualBudget, programmers
        )
    }

    override fun toDTO(item: Department): DepartmentDTO {
        Utils().makeSureTheseAreIds(item.id, item.boss_id)
        val boss = ProgrammerRepository().getById(item.boss_id)
        Utils().makeSureThisGuyIsDepBoss(boss)
        var programmers = Utils().getProgrammers(item.bossHistory_ids)
        if (programmers.isNullOrEmpty()) programmers = listOf(boss)
        if (!(programmers.contains(boss))) programmers.plus(boss)
        val projects = Utils().getProjects(item.finishedProjects_ids+item.developingProjects_ids)
        val finishedProjects = projects?.filter {
            project:Project -> Utils().intToBoolean(project.isFinished) }
        val developingProjects = projects?.filter {
            project:Project -> !Utils().intToBoolean(project.isFinished) }
        var anualBudget = item.budget
        developingProjects?.forEach { anualBudget += it.budget}
        return DepartmentDTO(
                item.id, item.name, boss, item.budget,
                finishedProjects, developingProjects,
                anualBudget, programmers
        )
    }
}