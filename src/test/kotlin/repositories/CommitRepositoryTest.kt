package repositories

import db.DBController
import model.Commit
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Commit Repository")
class CommitRepositoryTest {
    val commitPRE1 = Commit(
    "comm0001-0000-0000-0000-000000000000", "commit 1", null, "11/11/2001",
    "repo0001-0000-0000-0000-000000000000", "proj0001-0000-0000-0000-000000000000",
    "prog0001-0000-0000-0000-000000000000", "issu0001-0000-0000-0000-000000000000"
    )
    val commitPRE2 = Commit(
    "comm0002-0000-0000-0000-000000000000", "commit 2", "adsfasfa", "11/11/2001",
    "repo0002-0000-0000-0000-000000000000", "proj0002-0000-0000-0000-000000000000",
    "prog0001-0000-0000-0000-000000000000", "issu0002-0000-0000-0000-000000000000"
    );



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
        println(com.id)
        println("${result.id}\n")
        assertEquals(com.id, result.id)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun issueFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(commitPRE1.id)
        expectedList.add(commitPRE2.id)
        expectedList.add(com.id)
        println(expectedList)
        val actualList = CommitRepository().findAll()
        println("$actualList\n")
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        assertEquals(expectedList, res)
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