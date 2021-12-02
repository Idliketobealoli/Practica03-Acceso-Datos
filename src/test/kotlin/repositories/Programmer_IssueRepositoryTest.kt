package repositories

import db.DBController
import model.Programmer_Issue
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Programmer_Issue Repository")
class Programmer_IssueRepositoryTest {
    val pi1 = Programmer_Issue(
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
            "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb",
            "cccccccc-cccc-cccc-cccc-cccccccccccc"
    )
    val pi2 = Programmer_Issue(
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
    fun piInsertWorksFine() {
        val result = Programmer_IssueRepository().insert(pi1)
        println("$result\n")
        assertEquals(pi1, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun piGetByIdWorksFine() {
        val result = Programmer_IssueRepository().getById(pi1.id)
        println("$result\n")
        assertEquals(pi1.id, result.id)
    }

    @Test
    @DisplayName("Get by Programmer id")
    @Order(3)
    fun piGetByProgrammerIdWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pi1.programmer_id)
        val result = Programmer_IssueRepository().getByProgrammerId(pi1.programmer_id)
        val res = ArrayList<String>()
        result.forEach { x -> res.add(x.programmer_id) }
        println("$result\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Get by Issue id")
    @Order(4)
    fun piGetByIssueIdWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pi1.issue_id)
        val result = Programmer_IssueRepository().getByIssueId(pi1.issue_id)
        val res = ArrayList<String>()
        result.forEach { x -> res.add(x.issue_id) }
        println("$result\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Find all")
    @Order(5)
    fun piFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(pi1.id)
        println(expectedList)
        val actualList = Programmer_IssueRepository().findAll()
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        println("$actualList\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(6)
    fun piUpdateWorksFine() {
        val result = Programmer_IssueRepository().update(pi2)
        println(result)
        assertNotEquals(pi1, result)
        assertEquals(pi2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(7)
    fun piDeleteWorksFine() {
        val result = Programmer_IssueRepository().delete(pi2)
        assertEquals(pi2, result)
    }

    @Test
    @DisplayName("Delete all with Programmer id")
    @Order(8)
    fun piDeleteAllWithProgrammerIdWorksFine() {
        val v1 = Programmer_IssueRepository().insert(pi1)
        val v2 = Programmer_IssueRepository().insert(pi1)
        val v3 = Programmer_IssueRepository().insert(pi2)
        val result = Programmer_IssueRepository().deleteAllWithProgrammerId(pi1.programmer_id)
        println(result)
        assertNotEquals(0, result)
        assertEquals(2, result)
    }

    @Test
    @DisplayName("Delete all with Issue id")
    @Order(9)
    fun piDeleteAllWithIssueIdWorksFine() {
        val v1 = Programmer_IssueRepository().insert(pi1)
        val v2 = Programmer_IssueRepository().insert(pi1)
        val v3 = Programmer_IssueRepository().insert(pi2)
        val result = Programmer_IssueRepository().deleteAllWithIssueId(pi1.issue_id)
        println(result)
        assertNotEquals(0, result)
        assertEquals(2, result)
    }
}