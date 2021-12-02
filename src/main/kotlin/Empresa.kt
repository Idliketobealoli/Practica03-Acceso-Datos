import controllers.*
import db.DBController
import dto.*
import model.*
import services.ProgrammerService
import utils.Utils
import java.io.File
import kotlin.system.exitProcess

object Empresa {
    val prog = Programmer(
            "prog0001-0000-0000-0000-000000000000", "prog", "26/05/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000,projDTO1-0000-0000-0000-000000000000",
            "comm0001-0000-0000-0000-000000000000,comm0002-0000-0000-0000-000000000000," +
                    "comm0003-0000-0000-0000-000000000000,comm0004-0000-0000-0000-000000000000",
            "issu0001-0000-0000-0000-000000000000",
            "JAVA,KOTLIN", 2.22, 0,0,1
    )
    val boss = Programmer(
            "prog0002-0000-0000-0000-000000000000", "boss", "06/05/2002",
            "depart01-0000-0000-0000-000000000000",
            null,
            null,
            null,
            "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    )
    val boss2 = Programmer(
            "prog0004-0000-0000-0000-000000000000", "boss2", "06/05/2002",
            "depart01-0000-0000-0000-000000000000",
            null,
            null,
            null,
            "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    )
    val manager = Programmer(
            "prog0003-0000-0000-0000-000000000000", "manager", "26/08/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000,projDTO1-0000-0000-0000-000000000000",
            null,
            "issu0001-0000-0000-0000-000000000000",
            "KOTLIN", 2222.22, 0,1,0
    )
    val proj1 = Project(
            "proj0001-0000-0000-0000-000000000000",
            "depart01-0000-0000-0000-000000000000", manager.id,
            "project 1", 3333.3, "02/02/2000", null,
            "JAVA,C", "repo0001-0000-0000-0000-000000000000",
            0, prog.id
    )
    val proj2 = Project(
            "proj0002-0000-0000-0000-000000000000",
            "depart01-0000-0000-0000-000000000000", manager.id,
            "project 2", 3333.3, "02/02/2000", "31/12/2020",
            "C", "repo0002-0000-0000-0000-000000000000",
            1, null
    )
    val repo1 = Repository(
            "repo0001-0000-0000-0000-000000000000", "repo 1", "22/02/6006",
            proj1.id, "comm0001-0000-0000-0000-000000000000,comm0003-0000-0000-0000-000000000000",
            "issu0001-0000-0000-0000-000000000000"
    )
    val repo2 = Repository(
            "repo0002-0000-0000-0000-000000000000", "repo 2", "22/02/6006",
            proj2.id, "comm0002-0000-0000-0000-000000000000,comm0004-0000-0000-0000-000000000000",
            "issu0002-0000-0000-0000-000000000000"
    )
    val issu1 = Issue(
            "issu0001-0000-0000-0000-000000000000", manager.id, "issue 1",
            "akaskjfnefbufbksfcjafiw", "22/02/2022",
            prog.id, proj1.id, repo1.id, 1
    )
    val issu2 = Issue(
            "issu0002-0000-0000-0000-000000000000", manager.id, "issue 2",
            null, "22/02/2022",
            prog.id, proj2.id, repo2.id, 1
    )
    val comm1 = Commit(
            "comm0001-0000-0000-0000-000000000000", "commit 1", null, "11/11/2001",
            repo1.id, proj1.id, prog.id, issu1.id
    )
    val comm2 = Commit(
            "comm0002-0000-0000-0000-000000000000", "commit 2", "adsfasfa", "11/11/2001",
            repo2.id, proj2.id, prog.id, issu2.id
    )
    val depart3 = Department(
            "depart02-0000-0000-0000-000000000000", "dep3", boss.id, 11.0,
            null, null,0.0, "",
    )
    /*
    val boss = Programmer(
            "prog0001-0000-0000-0000-000000000000", "boss1", "06/05/2002",
            "depart01-0000-0000-0000-000000000000",
            null, null, null,
            "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    )
    val programmer1 = Programmer (
            "program1-0000-0000-0000-000000000000", "programmer1", "26/05/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000,proj0002-0000-0000-0000-000000000000",
            null,
            null,
            "KOTLIN,CSHARP", -2.22, 0,0,1
            )
    val manager = Programmer (
            "manager1-0000-0000-0000-000000000000", "manager01", "26/05/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000,proj0002-0000-0000-0000-000000000000",
            null,
            null,
            "JAVA,CSHARP", -2.22, 0,1,0
            )
    val proj1 = Project (
            "proj0001-0000-0000-0000-000000000000",
            "depart01-0000-0000-0000-000000000000",
            "manager1-0000-0000-0000-000000000000",
            "project 1",3333.3,"02/02/2000",null,
            "JAVA,C","repo0001-0000-0000-0000-000000000000",
            0,"program1-0000-0000-0000-000000000000"
            )
    val proj2 = Project (
            "proj0002-0000-0000-0000-000000000000",
            "depart02-0000-0000-0000-000000000000",
            "manager2-0000-0000-0000-000000000000",
            "project 2", 33.3, "05/11/2005", "06/12/2012",
            "JAVA,C", "repo0002-0000-0000-0000-000000000000",
            1, "program2-0000-0000-0000-000000000000"
            )


    val departDTO = DepartmentDTO (
            "depart01-0000-0000-0000-00000000", "Department",
            boss,6969.0, listOf(proj1),null,
            0.0, listOf(programmer1)
            )
    val programmerDTO = ProgrammerDTO (

            )

     */

    fun checkService() {
        try {
            DBController.open()
            println(DBController.select("SELECT 'Hello World'")?.next())
            DBController.close()
        }
        // esto no deberia saltar nunca, al ser un fichero sqlite.
        catch (e: Exception) {
            println("Could not connect with Database: ${e.printStackTrace()}")
            exitProcess(1)
        }
    }

    /*
    fun insertCosas(s: String) {
        println(DepartmentController.insertDepartment(departDTO,s))
    }

    fun findAllCosas(s: String) {

    }

    fun getCosasById(s: String) {

    }

    fun updateCosas(s: String) {

    }

    fun deleteCosas(s: String) {

    }

     */

    init {
        val path = "${System.getProperty("user.dir")}${ File.separator}sql${ File.separator}database.sql"
        DBController.open()
        DBController.initData(path)
        DBController.close()
    }

    fun departments(x: String) {
        val depart = DepartmentDTO(
                "depart01-0000-0000-0000-000000000000", "dep1", boss, 11111.0,
                listOf(proj1, proj2), null,0.0, listOf(),
        )
        val depart2 = DepartmentDTO(
                "depart01-0000-0000-0000-000000000000", "dep2", boss2, -11111.0,
                listOf(proj1, proj2), null,0.0, listOf(),
        )

        println("INSERT Department:")
        println(DepartmentController.insertDepartment(depart,x))
        println("\n\nFIND ALL Departments:")
        println(DepartmentController.findAllDepartments(x))
        println("\n\nGET Department with ID = ${depart.id}:")
        println(DepartmentController.getDepartmentById(depart.id,x))
        println("\n\nUPDATE Department with ID = ${depart.id}:")
        println(DepartmentController.updateDepartment(depart2, x))
        println("\n\nDELETE Department with ID = ${depart.id}:")
        println(DepartmentController.deleteDepartment(depart2,x))
        println("\n\n\n")
    }

    fun projects(x: String) {
        val projDto1 = ProjectDTO("projDTO1-0000-0000-0000-000000000000", depart3, manager,
                "project uwu", 64563.3, "01/01/2001", null,
                listOf(Technology.TYPESCRIPT,Technology.PYTHON), repo1,
                false, listOf(prog)
        )
        val projDto2 = ProjectDTO(projDto1.id, depart3, manager,
                "project modified", 111111.3, "01/01/2001", null,
                listOf(Technology.TYPESCRIPT,Technology.PHP,Technology.PYTHON), repo1,
                false, listOf(prog)
        )

        println("INSERT Project:")
        println(ProjectController.insertProject(projDto1,x))
        println("\n\nFIND ALL Projects:")
        println(ProjectController.findAllProjects(x))
        println("\n\nGET Project with ID = ${projDto1.id}:")
        println(ProjectController.getProjectById(projDto1.id,x))
        println("\n\nUPDATE Project with ID = ${projDto2.id}:")
        println(ProjectController.updateProject(projDto2, x))
        println("\n\nDELETE Project with ID = ${projDto2.id}:")
        println(ProjectController.deleteProject(projDto2,x))
        println("\n\n\n")
    }

    fun commits(x: String) {
        val commDTO1 = CommitDTO(
                "comm0003-0000-0000-0000-000000000000", "commit dto 1", null, "11/11/2001",
                repo1, proj1, prog, issu1
        )
        val commDTO2 = CommitDTO(
                "comm0003-0000-0000-0000-000000000000", "commit dto editado", "adsafghgfdgfbxvasfgrehtgfbvdfgehtgfbsgsfasfa", "11/11/2001",
                repo1, proj1, prog, issu1
        )

        println("INSERT Commit:")
        println(CommitController.insertCommit(commDTO1,x))
        println("\n\nFIND ALL Commits:")
        println(CommitController.findAllCommits(x))
        println("\n\nGET Commit with ID = ${commDTO1.id}:")
        println(CommitController.getCommitById(commDTO1.id,x))
        println("\n\nUPDATE Commit with ID = ${commDTO2.id}:")
        println(CommitController.updateCommit(commDTO2, x))
        println("\n\nDELETE Commit with ID = ${commDTO2.id}:")
        println(CommitController.deleteCommit(commDTO2,x))
        println("\n\n\n")
    }

    fun issues(x: String) {
        val issuDTO1 = IssueDTO(
                "issu0004-0000-0000-0000-000000000000", manager, "Issue DTO 1",
                "https://www.youtube.com/watch?v=hjGZLnja1o8&ab_channel=TheNightcoreWitcher",
                "26/05/2002", null, proj1, repo1, false
        )
        val issuDTO2 = IssueDTO(
                "issu0004-0000-0000-0000-000000000000", manager, "Issue DTO modified",
                null,
                "26/06/2002", null, proj1, repo1, true
        )

        println("INSERT Issues:")
        println(IssueController.insertIssue(issuDTO1,x))
        println("\n\nFIND ALL Issues:")
        println(IssueController.findAllIssues(x))
        println("\n\nGET Issue with ID = ${issuDTO1.id}:")
        println(IssueController.getIssueById(issuDTO1.id,x))
        println("\n\nUPDATE Issue with ID = ${issuDTO2.id}:")
        println(IssueController.updateIssue(issuDTO2, x))
        println("\n\nDELETE Issue with ID = ${issuDTO2.id}:")
        println(IssueController.deleteIssue(issuDTO2,x))
        println("\n\n\n")
    }

    //TODO: CASAR COSAS
    fun programmers(x: String) {
        /*
        println("INSERT Issues:")
        println(CommitController.insertCommit(commDTO1,x))
        println("\n\nFIND ALL Issues:")
        println(CommitController.findAllCommits(x))
        println("\n\nGET Issue with ID = ${commDTO1.id}:")
        println(CommitController.getCommitById(commDTO1.id,x))
        println("\n\nUPDATE Issue with ID = ${commDTO2.id}:")
        println(CommitController.updateCommit(commDTO2, x))
        println("\n\nDELETE Issue with ID = ${commDTO2.id}:")
        println(CommitController.deleteCommit(commDTO2,x))
        println("\n\n\n")

         */
    }

    //TODO: CASAR COSAS
    fun repositories(x: String) {
        /*
        println("INSERT Issues:")
        println(CommitController.insertCommit(commDTO1,x))
        println("\n\nFIND ALL Issues:")
        println(CommitController.findAllCommits(x))
        println("\n\nGET Issue with ID = ${commDTO1.id}:")
        println(CommitController.getCommitById(commDTO1.id,x))
        println("\n\nUPDATE Issue with ID = ${commDTO2.id}:")
        println(CommitController.updateCommit(commDTO2, x))
        println("\n\nDELETE Issue with ID = ${commDTO2.id}:")
        println(CommitController.deleteCommit(commDTO2,x))
        println("\n\n\n")

         */
    }
}