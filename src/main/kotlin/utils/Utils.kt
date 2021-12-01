package utils

import Technology
import dto.ProgrammerDTO
import model.*
import repositories.*
import java.util.*

class Utils {
    fun intToBoolean(x : Int) : Boolean {
        return when (x % 2) {
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
            if (!(arg.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())))
                throw IllegalArgumentException("Error: The introduced value is not a valid id")
        }
    }

    fun getIssues(issuesIds: String?): List<Issue>? {
        val listIssues = issuesIds?.split(",")
        val listIssuesResult = ArrayList<Issue>()
        if (!listIssues.isNullOrEmpty()) {
            for (id in listIssues) {
                if (id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex()))
                    listIssuesResult.add(IssueRepository().getById(id))
            }
        }
        return if (listIssuesResult.isNotEmpty()) listIssuesResult else null
    }

    fun getCommits(commitsIds: String?): List<Commit>? {
        val listCommits = commitsIds?.split(",")
        val listCommitsResult = ArrayList<Commit>()
        if (!listCommits.isNullOrEmpty()) {
            for (id in listCommits) {
                if (id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    listCommitsResult.add(CommitRepository().getById(id))
                }
            }
        }
        return if (listCommitsResult.isNotEmpty()) listCommitsResult else null
    }

    fun getTechnologies(technologies: String?): List<Technology>? {
        val listTechnologies = technologies?.split(",")
        val listTechnologiesResult = ArrayList<Technology>()
        if (!listTechnologies.isNullOrEmpty()) {
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
        var str = projectsIds
        if (projectsIds?.trim()?.endsWith(",") == true) str = projectsIds.trim().dropLast(1)
        var listProjectsIds = str?.split(",")
        val listProjects = ArrayList<Project>()
        if (listProjectsIds?.get(0).contentEquals("null")) listProjectsIds = listOf()
        if (!listProjectsIds.isNullOrEmpty()) {
            for (id in listProjectsIds) {
                if (id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    listProjects.add(ProjectRepository().getById(id))
                }
            }
        }
        return if (listProjects.isNotEmpty()) listProjects else null
    }

    fun getTechnologiesAsString(technologies: List<Technology>?): String {
        var result = ""
        return if (!technologies.isNullOrEmpty()) {
            for (technology in technologies) {
                result+="${technology.name},"
            }
            result
        }
        else result
    }

    fun getProjectsIDS(projects: List<Project>?): String {
        var result = ""
        return if (!projects.isNullOrEmpty()) {
            for (project in projects) {
                if (project.id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    result+="${project.id},"
                }
                else throw Exception(
                        "Error at Utils.getProjectsIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else result
    }

    fun getCommitsIDS(commits: List<Commit>?): String {
        var result = ""
        return if (!commits.isNullOrEmpty()) {
            for (commit in commits) {
                if (commit.id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    result+="${commit.id},"
                }
                else throw Exception(
                        "Error at Utils.getCommitsIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else result
    }

    fun getIssuesIDS(issues: List<Issue>?): String {
        var result = ""
        return if (!issues.isNullOrEmpty()) {
            for (issue in issues) {
                if (issue.id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    result+="${issue.id},"
                }
                else throw Exception(
                        "Error at Utils.getIssuesIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else result
    }

    fun getProgrammersIDS(programmers: List<Programmer>?): String {
        var result = ""
        return if (!programmers.isNullOrEmpty()) {
            for (programmer in programmers) {
                if (programmer.id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    result+="${programmer.id},"
                }
                else throw Exception(
                        "Error at Utils.getProgrammersIDS: " +
                                "Non-valid id"
                )
            }
            result
        }
        else result
    }

    fun getProgrammers(programmersIds: String?): List<Programmer>? {
        val listProgrammersIds = programmersIds?.split(",")
        val listProgrammers = ArrayList<Programmer>()
        if (!listProgrammersIds.isNullOrEmpty()) {
            for (id in listProgrammersIds) {
                if (id.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    listProgrammers.add(ProgrammerRepository().getById(id))
                }
            }
        }
        return if (listProgrammers.isNotEmpty()) listProgrammers else null
    }

    fun makeSureThisGuyIsDepBoss(boss: Programmer) {
        when {
            !intToBoolean(boss.isDepBoss) ->
                throw Exception("Error: programmer with id ${boss.id} is not a Department boss.")
        }
        makeSureBooleansAreCorrect(boss)
    }

    fun makeSureThisProgrammerIsInThisIssue(idProgrammer: String, idIssue: String) {
        val issue = IssueRepository().getById(idIssue)
        val list = issue.programmers_ids?.split(",")
        if (list?.contains(idProgrammer) != true) throw Exception("Error: programmer with id $idProgrammer is not in Issue[$idIssue].programmers")
    }

    fun makeSureThisGuyIsProjectManager(author: Programmer, id: String) {
        when {
            !intToBoolean(author.isProjectManager) ->
                throw Exception("Error: programmer with id ${author.id} is not a Project Manager.")
            author.activeProjects_ids?.contains(id) != true ->
                throw Exception("Error: programmer with id ${author.id} is not in this project.")
        }
        makeSureBooleansAreCorrect(author)
    }
}