package mappers

import Technology
import dto.ProgrammerDTO
import model.Commit
import model.Issue
import model.Programmer
import model.Project
import repositories.CommitRepository
import repositories.DepartmentRepository
import repositories.IssueRepository
import repositories.ProjectRepository
import utils.Utils
import java.util.ArrayList

class ProgrammerMapper : BaseMapper<Programmer, ProgrammerDTO>() {
    override fun fromDTO (item : ProgrammerDTO) : Programmer {
        return Programmer(
                item.id, item.name, item.registerDate,
                item.department.id, getActiveProjectsIDS(item),
                getCommitsIDS(item), getIssuesIDS(item),
                getTechnologiesAsString(item), item.salary,
                Utils().booleanToInt(item.isDepBoss),
                Utils().booleanToInt(item.isProjectManager),
                Utils().booleanToInt(item.isActive),
        )
    }

    private fun getTechnologiesAsString(item: ProgrammerDTO): String? {
        val result = ""
        if (!item.technologies.isNullOrEmpty()) {
            for (index in 0 until item.technologies!!.size) {
                result.plus("${item.technologies!![index].name},")
            }
            return result
        }
        else return null
    }

    private fun getIssuesIDS(item: ProgrammerDTO): String? {
        val result = ""
        if (!item.issues.isNullOrEmpty()) {
            for (index in 0 until item.issues!!.size) {
                if (item.issues!![index].id
                                .matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${item.issues!![index].id},")
                }
                else throw Exception(
                        "Error at ProgrammerMapper.fromDTO.getIssuesIDS: " +
                                "Non-valid id"
                )
            }
            return result
        }
        else return null
    }

    private fun getCommitsIDS(item: ProgrammerDTO): String? {
        val result = ""
        if (!item.commits.isNullOrEmpty()) {
            for (index in 0 until item.commits!!.size) {
                if (item.commits!![index].id
                                .matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${item.commits!![index].id},")
                }
                else throw Exception(
                        "Error at ProgrammerMapper.fromDTO.getCommitsIDS: " +
                                "Non-valid id"
                )
            }
            return result
        }
        else return null
    }

    private fun getActiveProjectsIDS(item: ProgrammerDTO): String? {
        val result = ""
        if (!item.activeProjects.isNullOrEmpty()) {
            for (index in 0 until item.activeProjects!!.size) {
                if (item.activeProjects!![index].id
                                .matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${item.activeProjects!![index].id},")
                }
                else throw Exception(
                        "Error at ProgrammerMapper.fromDTO.getActiveProjectsIDS: " +
                        "Non-valid id"
                )
            }
            return result
        }
        else return null
    }

    override fun toDTO (item : Programmer) : ProgrammerDTO {
        return ProgrammerDTO(
                item.id, item.name, item.registerDate,
                DepartmentRepository().getById(item.department_id),
                getActiveProjects(item), getCommits(item), getIssues(item),
                getTechnologies(item), item.salary,
                Utils().intToBoolean(item.isDepBoss),
                Utils().intToBoolean(item.isProjectManager),
                Utils().intToBoolean(item.isActive),
        )
    }

    private fun getTechnologies(item: Programmer): List<Technology>? {
        val listTechnologies = item.technologies?.split(",")
        val listTechnologiesResult = ArrayList<Technology>()
        if (listTechnologies != null) {
            for (t in listTechnologies) {
                val tech = getTech(t)
                if (tech != null) listTechnologiesResult.add(tech)
            }
        }
        return if (listTechnologiesResult.isNotEmpty())
            listTechnologiesResult else null
    }

    private fun getTech(t: String): Technology? {
        return when (t.toUpperCase()) {
            "JAVA" -> Technology.JAVA
            "KOTLIN" -> Technology.KOTLIN
            "PHP" -> Technology.PHP
            "PYTHON" -> Technology.PYTHON
            "CSHARP" -> Technology.CSHARP
            "JAVASCRIPT" -> Technology.JAVASCRIPT
            "TYPESCRIPT" -> Technology.TYPESCRIPT
            "C" -> Technology.C
            else -> null
        }
    }

    private fun getCommits(item: Programmer): List<Commit>? {
        val listCommits = item.commits_ids?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listCommitsResult = ArrayList<Commit>()
        if (listCommits != null) {
            for (id in listCommits) {
                //listCommitsResult.add(CommitRepository().getById(id))
            }
        }
        return if (listCommitsResult.isNotEmpty()) listCommitsResult else null
    }

    private fun getActiveProjects(item: Programmer): List<Project>? {
        val listAPids = item.activeProjects_ids?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listActiveProjects = ArrayList<Project>()
        if (listAPids != null) {
            for (id in listAPids) {
                listActiveProjects.add(ProjectRepository().getById(id))
            }
        }
        return if (listActiveProjects.isNotEmpty()) listActiveProjects else null
    }

    private fun getIssues(item: Programmer): List<Issue>? {
        val listIssues = item.issues_ids?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listIssuesResult = ArrayList<Issue>()
        if (listIssues != null) {
            for (id in listIssues) {
                //listIssuesResult.add(IssueRepository().getById(id))
            }
        }
        return if (listIssuesResult.isNotEmpty()) listIssuesResult else null
    }
}