package utils

import Technology
import dto.ProgrammerDTO
import model.Commit
import model.Issue
import model.Programmer
import model.Project
import repositories.CommitRepository
import repositories.IssueRepository
import repositories.ProgrammerRepository
import repositories.ProjectRepository
import java.util.*

class Utils {
    fun intToBoolean(x : Int) : Boolean {
        val res = x % 2
        return when (res) {
            0 -> false
            1 -> true
            else -> false
        }
    }

    fun booleanToInt(x: Boolean): Int {
        return when (x) {
            false -> 0
            true -> 1
        }
    }

    fun matchesDate(string: String) : String {
        val splittedString = string.split("/")
        val year = splittedString[2].toInt()
        val month = splittedString[1].toInt()
        val day = splittedString[0].toInt()
        return if (day in 1..31 && month in 1..12 && year in 1900..9999) string
        else throw Exception("Invalid date.")
    }

    fun matchesDateAcceptingNull (string: String?) : String? {
        return if (string.isNullOrBlank()) null
        else matchesDate(string)
    }

    fun makeSureBooleansAreCorrect(item: Programmer) {
        val depBoss = intToBoolean(item.isDepBoss)
        val pManager = intToBoolean(item.isProjectManager)
        val active = intToBoolean(item.isActive)
        when {
            depBoss && pManager -> throw Exception(
                    "A programmer can't be Department Boss and Project Manager at the same time."
            )
            depBoss && active -> throw Exception(
                    "A Department Boss can't be active at the same time."
            )
            pManager && active -> throw Exception(
                    "A Project Manager can't be active at the same time."
            )
            depBoss && pManager && active -> throw Exception(
                    "A programmer can't be Department Boss, Project Manager and be active at the same time."
            )
        }
    }

    fun makeSureBooleansAreCorrect(item: ProgrammerDTO) {
        when {
            item.isDepBoss && item.isProjectManager -> throw Exception(
                    "A programmer can't be Department Boss and Project Manager at the same time."
            )
            item.isDepBoss && item.isActive -> throw Exception(
                    "A Department Boss can't be active at the same time."
            )
            item.isProjectManager && item.isActive -> throw Exception(
                    "A Project Manager can't be active at the same time."
            )
            item.isProjectManager && item.isProjectManager && item.isActive -> throw Exception(
                    "A programmer can't be Department Boss, Project Manager and be active at the same time."
            )
        }
    }

    fun makeSureTheseAreIds(vararg args: String) {
        for (arg in args) {
            if (!(arg.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())))
                throw IllegalArgumentException("Error: The introduced value is not a valid id")
        }
    }

    fun getIssues(issuesIds: String?): List<Issue>? {
        val listIssues = issuesIds?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listIssuesResult = ArrayList<Issue>()
        if (listIssues != null) {
            for (id in listIssues) {
                listIssuesResult.add(IssueRepository().getById(id))
            }
        }
        return if (listIssuesResult.isNotEmpty()) listIssuesResult else null
    }

    fun getCommits(commitsIds: String?): List<Commit>? {
        val listCommits = commitsIds?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listCommitsResult = ArrayList<Commit>()
        if (listCommits != null) {
            for (id in listCommits) {
                listCommitsResult.add(CommitRepository().getById(id))
            }
        }
        return if (listCommitsResult.isNotEmpty()) listCommitsResult else null
    }

    fun getTechnologies(technologies: String?): List<Technology>? {
        val listTechnologies = technologies?.split(",")
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

    fun getProjects(projectsIds: String?): List<Project>? {
        val listProjectsIds = projectsIds?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listProjects = ArrayList<Project>()
        if (listProjectsIds != null) {
            for (id in listProjectsIds) {
                listProjects.add(ProjectRepository().getById(id))
            }
        }
        return if (listProjects.isNotEmpty()) listProjects else null
    }

    fun getTechnologiesAsString(technologies: List<Technology>?): String? {
        val result = ""
        return if (!technologies.isNullOrEmpty()) {
            for (technology in technologies) {
                result.plus("${technology.name},")
            }
            result
        }
        else null
    }

    fun getProjectsIDS(projects: List<Project>?): String? {
        val result = ""
        return if (!projects.isNullOrEmpty()) {
            for (project in projects) {
                if (project.id.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${project.id},")
                }
                else throw Exception(
                        "Error at Utils.getProjectsIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else null
    }

    fun getCommitsIDS(commits: List<Commit>?): String? {
        val result = ""
        return if (!commits.isNullOrEmpty()) {
            for (commit in commits) {
                if (commit.id.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${commit.id},")
                }
                else throw Exception(
                        "Error at Utils.getCommitsIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else null
    }

    fun getIssuesIDS(issues: List<Issue>?): String? {
        val result = ""
        return if (!issues.isNullOrEmpty()) {
            for (issue in issues) {
                if (issue.id.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${issue.id},")
                }
                else throw Exception(
                        "Error at Utils.getIssuesIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else null
    }

    fun getProgrammersIDS(programmers: List<Programmer>?): String? {
        val result = ""
        return if (!programmers.isNullOrEmpty()) {
            for (programmer in programmers) {
                if (programmer.id.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}".toRegex())) {
                    result.plus("${programmer.id},")
                }
                else throw Exception(
                        "Error at Utils.getProgrammersIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else null
    }

    fun getProgrammers(programmersIds: String?): List<Programmer>? {
        val listProgrammersIds = programmersIds?.split(
                "[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"
                        .toRegex(), 0)
        val listProgrammers = ArrayList<Programmer>()
        if (listProgrammersIds != null) {
            for (id in listProgrammersIds) {
                listProgrammers.add(ProgrammerRepository().getById(id))
            }
        }
        return if (listProgrammers.isNotEmpty()) listProgrammers else null
    }
}