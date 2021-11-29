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
        assertEquals(project, result)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun projectFindAllWorksFine() {
        val expectedList = listOf(project)
        println(expectedList)
        val actualList = ProjectRepository().findAll()
        println("$actualList\n")
        assertEquals(expectedList, actualList)
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