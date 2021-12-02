package repositories

import db.DBController
import model.Project_Programmer
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Project_Programmer Repository")
class Project_ProgrammerRepositoryTest {
    val pp1 = Project_Programmer(
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
            "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb",
            "cccccccc-cccc-cccc-cccc-cccccccccccc"
    )
    val pp2 = Project_Programmer(
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
            "cccccccc-cccc-cccc-cccc-cccccccccccc",
            "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"
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
    fun ppInsertWorksFine() {
        val result = Project_ProgrammerRepository().insert(pp1)
        println("$result\n")
        assertEquals(pp1, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun ppGetByIdWorksFine() {
        val result = Project_ProgrammerRepository().getById(pp1.id)
        println("$result\n")
        assertEquals(pp1.id, result.id)
    }

    @Test
    @DisplayName("Get by Programmer id")
    @Order(3)
    fun ppGetByProgrammerIdWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pp1.programmer_id)
        val result = Project_ProgrammerRepository().getByProgrammerId(pp1.programmer_id)
        val res = ArrayList<String>()
        result.forEach { x -> res.add(x.programmer_id) }
        println("$result\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Get by Project id")
    @Order(4)
    fun ppGetByProjectIdWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pp1.project_id)
        val result = Project_ProgrammerRepository().getByProjectId(pp1.project_id)
        val res = ArrayList<String>()
        result.forEach { x -> res.add(x.project_id) }
        println("$result\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Find all")
    @Order(5)
    fun ppFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pp1.id)
        println(expectedList)
        val actualList = Project_ProgrammerRepository().findAll()
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        println("$actualList\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(6)
    fun ppUpdateWorksFine() {
        val result = Project_ProgrammerRepository().update(pp2)
        println(result)
        assertNotEquals(pp1, result)
        assertEquals(pp2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(7)
    fun ppDeleteWorksFine() {
        val result = Project_ProgrammerRepository().delete(pp2)
        assertEquals(pp2, result)
    }

    @Test
    @DisplayName("Delete all with Project id")
    @Order(8)
    fun ppDeleteAllWithProjectIdWorksFine() {
        val v1 = Project_ProgrammerRepository().insert(pp1)
        val v2 = Project_ProgrammerRepository().insert(pp1)
        val v3 = Project_ProgrammerRepository().insert(pp2)
        val result = Project_ProgrammerRepository().deleteAllWithProjectId(pp1.project_id)
        println(result)
        assertNotEquals(0, result)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("Delete all with Programmer id")
    @Order(9)
    fun ppDeleteAllWithProgrammerIdWorksFine() {
        val v1 = Project_ProgrammerRepository().insert(pp1)
        val v2 = Project_ProgrammerRepository().insert(pp1)
        val v3 = Project_ProgrammerRepository().insert(pp2)
        val result = Project_ProgrammerRepository().deleteAllWithProgrammerId(pp1.programmer_id)
        println(result)
        assertNotEquals(0, result)
        assertEquals(2, result)
    }
}