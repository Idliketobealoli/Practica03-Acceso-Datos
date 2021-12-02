package repositories

import db.DBController
import model.Project
import model.Repository
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Project Repository")
class ProjectRepositoryTest {
    val projectPRE1 = Project(
    "proj0001-0000-0000-0000-000000000000",
    "depart02-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000",
    "project 1", 3333.3, "02/02/2000", null,
    "JAVA,C", "repo0001-0000-0000-0000-000000000000",
    0, "prog0001-0000-0000-0000-000000000000"
    );
    val projectPRE2 = Project(
    "proj0002-0000-0000-0000-000000000000",
    "depart02-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000",
    "project 2", 3333.3, "02/02/2000", "31/12/2020",
    "C", "repo0002-0000-0000-0000-000000000000",
    1, null
    );

    val project = Project(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "12345678-1234-1234-1234-123456789012",
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa", "project 1", -909090.90,
            "26/05/2002", null, "JAVA,KOTLIN",
            "12345678-1234-1234-1234-123456789012", 0,
            "11111111-1111-1111-1111-111111111111,22222222-2222-2222-2222-222222222222"
    )
    val project2 = Project(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "11111111-1111-1111-1111-111111111111",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx", "project 2", 10.0,
            "26/01/1999", "31/12/2222", "JAVA,KOTLIN,PYTHON",
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa", 1,
            "22222222-2222-2222-2222-222222222222"
    )

    companion object {
        @JvmStatic
        @BeforeAll
        fun initialize() {
            DBController.open()
            DBController.initData("${System.getProperty("user.dir")}${ File.separator}sql${ File.separator}database.sql")
            DBController.close()
        }
    }

    @BeforeEach
    fun connect() {
        DBController.open()
    }
    @AfterEach
    fun disconnect() {
        DBController.close()
    }

    @Test
    @DisplayName("Insert")
    @Order(1)
    fun projectInsertWorksFine() {
        val result = ProjectRepository().insert(project)
        println("$result\n")
        assertEquals(project, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun projectGetByIdWorksFine() {
        val result = ProjectRepository().getById(project.id)
        println("$result\n")
        assertEquals(project.id, result.id)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun projectFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(projectPRE1.id)
        expectedList.add(projectPRE2.id)
        expectedList.add(project.id)
        println(expectedList)
        val actualList = ProjectRepository().findAll()
        println("$actualList\n")
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun projectUpdateWorksFine() {
        val result = ProjectRepository().update(project2)
        println(result)
        assertNotEquals(project, result)
        assertEquals(project2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun projectDeleteWorksFine() {
        val result = ProjectRepository().delete(project2)
        assertEquals(project2, result)
    }
}