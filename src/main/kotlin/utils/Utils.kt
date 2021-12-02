package utils

import Technology
import dto.ProgrammerDTO
import model.*
import repositories.*
import java.util.*

/**
 * Clase de utilidades.
 */
class Utils {
    /**
     * Pasa de int a booleano.
     * Si el integer es par, lo asigna a false, si es impar, lo asigna a verdadero.
     */
    fun intToBoolean(x : Int) : Boolean {
        return when (x % 2) {
            0 -> false
            1 -> true
            else -> false
        }
    }

    /**
     * Pasa de boolean a int. false == 0 y true == 1
     */
    fun booleanToInt(x: Boolean): Int {
        return when (x) {
            false -> 0
            true -> 1
        }
    }

    /**
     * Se asegura de que la string introducida corresponde a una fecha entre el 01/01/1900 y el 31/12/9999 dd/MM/yyyy,
     * separados por barras y obligatoriamente dia es un rango de 1 a 31, mes es de 1 a 12 y año de 1900 a 9999.
     * Si no cumple este criterio, suelta excepcion.
     */
    fun matchesDate(string: String) : String {
        val splittedString = string.split("/")
        val year = splittedString[2].toInt()
        val month = splittedString[1].toInt()
        val day = splittedString[0].toInt()
        return if (day in 1..31 && month in 1..12 && year in 1900..9999) string
        else throw Exception("Invalid date.")
    }

    /**
     * Si le pasas un nulo o cadena vacía, returnea nulo. De lo contrario llama al metodo matchesDate y returnea el
     * resultado de dicho metodo.
     * @see matchesDate
     */
    fun matchesDateAcceptingNull (string: String?) : String? {
        return if (string.isNullOrBlank()) null
        else matchesDate(string)
    }

    /**
     * Se asegura de que los booleanos de un objeto Programmer son correctos. De lo contrario, excepcion.
     * Si son correctos no hace nada.
     */
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

    /**
     * Lo mismo que el metodo de su mismo nombre pero para ProgrammerDTO
     * @see makeSureBooleansAreCorrect
     */
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

    /**
     * Se asegura de que la string introducida en cada argumento cumpla con la expresión regular del UUID.
     * En caso de no hacerlo, excepcion.
     */
    fun makeSureTheseAreIds(vararg args: String) {
        for (arg in args) {
            if (!(arg.matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())))
                throw IllegalArgumentException("Error: The introduced value is not a valid id")
        }
    }

    /**
     * Coge la string pasada por parametro y la convierte en una lista de issues, buscandolas por ID. La string ha de
     * contener las ids de las issues y estas deben estar separadas por comas.
     */
    fun getIssues(issuesIds: String?): List<Issue>? {
        val listIssues = issuesIds?.split(",")
        val listIssuesResult = ArrayList<Issue>()
        if (!listIssues.isNullOrEmpty()) {
            for (id in listIssues) {
                if (id.trim().matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex()))
                    listIssuesResult.add(IssueRepository().getById(id))
            }
        }
        return if (listIssuesResult.isNotEmpty()) listIssuesResult else null
    }

    /**
     * Igual que getIssues pero para commits
     * @see getIssues
     */
    fun getCommits(commitsIds: String?): List<Commit>? {
        val listCommits = commitsIds?.split(",")
        val listCommitsResult = ArrayList<Commit>()
        if (!listCommits.isNullOrEmpty()) {
            for (id in listCommits) {
                if (id.trim().matches("[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}".toRegex())) {
                    listCommitsResult.add(CommitRepository().getById(id))
                }
            }
        }
        return if (listCommitsResult.isNotEmpty()) listCommitsResult else null
    }

    /**
     * Lo mismos que las anteriores, pero para technology
     * @see getCommits
     */
    fun getTechnologies(technologies: String?): List<Technology>? {
        val listTechnologies = technologies?.split(",")
        val listTechnologiesResult = ArrayList<Technology>()
        if (!listTechnologies.isNullOrEmpty()) {
            for (t in listTechnologies) {
                val tech = getTech(t.trim())
                if (tech != null) listTechnologiesResult.add(tech)
            }
        }
        return if (listTechnologiesResult.isNotEmpty())
            listTechnologiesResult else null
    }

    /**
     * Le pasas un String y te returnea una tecnología, o nulo.
     */
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

    /**
     * Igual que getIssues pero para projects
     * @see getIssues
     */
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

    /**
     * Le pasas una lista de technologies y te las devuelve en formato String, o devuelve una cadena vacia.
     */
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

    /**
     * Le pasas una lista de projects y las devuelve como string, o returnea cadena vacia.
     */
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

    /**
     * Igual que getProjectsIDS pero para commits.
     * @see getProjectsIDS
     */
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

    /**
     * Igual que getProjectsIDS pero para issues.
     * @see getProjectsIDS
     */
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

    /**
     * Igual que getProjectsIDS pero para programmers.
     * @see getProjectsIDS
     */
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

    /**
     * Igual que getIssues pero para programmers.
     * @see getIssues
     */
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

    /**
     * Se asegura de que el programmer introducido es jefe de departamento. En caso negativo, excepcion.
     */
    fun makeSureThisGuyIsDepBoss(boss: Programmer) {
        when {
            !intToBoolean(boss.isDepBoss) ->
                throw Exception("Error: programmer with id ${boss.id} is not a Department boss.")
        }
        makeSureBooleansAreCorrect(boss)
    }

    /**
     * Se asegura de que el programador introducido este en la issue introducida.
     */
    fun makeSureThisProgrammerIsInThisIssue(idProgrammer: String, idIssue: String) {
        val issue = IssueRepository().getById(idIssue)
        val list = issue.programmers_ids?.split(",")
        if (list?.contains(idProgrammer) != true) throw Exception("Error: programmer with id $idProgrammer is not in Issue[$idIssue].programmers")
    }

    /**
     * Se asegura de que el programmer introducido es jefe de proyecto. En caso negativo, excepcion.
     */
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