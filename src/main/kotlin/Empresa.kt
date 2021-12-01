import controllers.DepartmentController
import controllers.ProjectController
import db.DBController
import dto.DepartmentDTO
import dto.ProgrammerDTO
import dto.ProjectDTO
import model.*
import utils.Utils
import java.io.File
import kotlin.system.exitProcess

object Empresa {
    val prog = Programmer(
            "prog0001-0000-0000-0000-000000000000", "prog", "26/05/2002",
            "depart01-0000-0000-0000-000000000000",
            "proj0001-0000-0000-0000-000000000000,projDTO1-0000-0000-0000-000000000000",
            "comm0001-0000-0000-0000-000000000000,comm0002-0000-0000-0000-000000000000",
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
            proj1.id, "comm0001-0000-0000-0000-000000000000",
            "issu0001-0000-0000-0000-000000000000"
    )
    val repo2 = Repository(
            "repo0002-0000-0000-0000-000000000000", "repo 2", "22/02/6006",
            proj2.id, "comm0002-0000-0000-0000-000000000000",
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
            null, proj2.id, repo2.id, 1
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
    val depart = DepartmentDTO(
            "depart01-0000-0000-0000-000000000000", "dep1", boss, 11111.0,
            listOf(proj1, proj2), null,0.0, listOf(),
    )
    val depart2 = DepartmentDTO(
            "depart01-0000-0000-0000-000000000000", "dep2", boss2, -11111.0,
            listOf(proj1, proj2), null,0.0, listOf(),
    )
    val projDto1 = ProjectDTO("projDTO1-0000-0000-0000-000000000000", depart3, manager,
            "project uwu", 64563.3, "01/01/2001", null,
            listOf(Technology.TYPESCRIPT,Technology.PYTHON), repo1,
            false, listOf(prog))
    val projDto2 = ProjectDTO(projDto1.id, depart3, manager,
            "project modified", 111111.3, "01/01/2001", null,
            listOf(Technology.TYPESCRIPT,Technology.PHP,Technology.PYTHON), repo1,
            false, listOf(prog))

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

    init {
        val path = "${System.getProperty("user.dir")}${ File.separator}sql${ File.separator}database.sql"
        DBController.open()
        DBController.initData(path)
        DBController.close()
    }

    fun departmentsXML() {
        println("INSERT Department:")
        println(DepartmentController.insertDepartment(depart,"xml"))
        println("\n\nFIND ALL Departments:")
        println(DepartmentController.findAllDepartments("xml"))
        println("\n\nGET Department with ID = ${depart.id}:")
        println(DepartmentController.getDepartmentById(depart.id,"xml"))
        println("\n\nUPDATE Department with ID = ${depart.id}:")
        println(DepartmentController.updateDepartment(depart2, "xml"))
        println("\n\nDELETE Department with ID = ${depart.id}:")
        println(DepartmentController.deleteDepartment(depart2,"xml"))
    }

    fun departmentsJSON() {
        println("INSERT Department:")
        println(DepartmentController.insertDepartment(depart,"json"))
        println("\n\nFIND ALL Departments:")
        println(DepartmentController.findAllDepartments("json"))
        println("\n\nGET Department with ID = ${depart.id}:")
        println(DepartmentController.getDepartmentById(depart.id,"json"))
        println("\n\nUPDATE Department with ID = ${depart.id}:")
        println(DepartmentController.updateDepartment(depart2, "json"))
        println("\n\nDELETE Department with ID = ${depart.id}:")
        println(DepartmentController.deleteDepartment(depart2,"json"))
    }


    fun projectsXML() {
        println("INSERT Project:")
        println(ProjectController.insertProject(projDto1,"xml"))
        println("\n\nFIND ALL Projects:")
        println(ProjectController.findAllProjects("xml"))
        println("\n\nGET Project with ID = ${projDto1.id}:")
        println(ProjectController.getProjectById(projDto1.id,"xml"))
        println("\n\nUPDATE Project with ID = ${projDto2.id}:")
        println(ProjectController.updateProject(projDto2, "xml"))
        println("\n\nDELETE Project with ID = ${projDto2.id}:")
        println(ProjectController.deleteProject(projDto2,"xml"))
    }

    fun projectsJSON() {
        println("INSERT Project:")
        println(ProjectController.insertProject(projDto1, "json"))
        println("\n\nFIND ALL Projects:")
        println(ProjectController.findAllProjects("json"))
        println("\n\nGET Project with ID = ${projDto1.id}:")
        println(ProjectController.getProjectById(projDto1.id, "json"))
        println("\n\nUPDATE Project with ID = ${projDto2.id}:")
        println(ProjectController.updateProject(projDto2, "json"))
        println("\n\nDELETE Project with ID = ${projDto2.id}:")
        println(ProjectController.deleteProject(projDto2, "json"))
    }
}