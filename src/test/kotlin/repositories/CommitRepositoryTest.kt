package repositories

import db.DBController
import model.Commit
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Department Repository")
class CommitRepositoryTest {
    val com = Commit(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Commit 1",
            null,
            "26/05/2002",
            "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy",
            "tttttttt-tttt-tttt-tttt-tttttttttttt",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
            "22222222-xxxx-x1xx-xxxx-xxxxxx1xxxx1"
    )
    val com2 = Commit(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Commit 2",
            "uwu",
            "26/06/2222",
            "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyy1yyy",
            "tttttttt-tttt-tttt-tttt-ttttttt1tttt",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxx1xxxxx",
            "22222222-xxxx-x1xx-xxxx-xxx11x1xxxx1"
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
    fun departmentInsertWorksFine() {
        val result = CommitRepository().insert(com)
        println("$result\n")
        assertEquals(com, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun departmentGetByIdWorksFine() {
        val result = CommitRepository().getById(com.id)
        println("$result\n")
        assertEquals(com, result)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun issueFindAllWorksFine() {
        val expectedList = listOf(com)
        println(expectedList)
        val actualList = CommitRepository().findAll()
        println("$actualList\n")
        assertEquals(expectedList, actualList)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun issueUpdateWorksFine() {
        val result = CommitRepository().update(com2)
        println(result)
        assertNotEquals(com, result)
        assertEquals(com2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun issueDeleteWorksFine() {
        val result = CommitRepository().delete(com2)
        assertEquals(com2, result)
    }
}