package repositories

import db.DBController
import model.Issue
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Issue Repository")
class IssueRepositoryTest {
    val issuePRE1 = Issue(
    "issu0001-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000", "issue 1",
    "akaskjfnefbufbksfcjafiw", "22/02/2022",
    "prog0001-0000-0000-0000-000000000000", "proj0001-0000-0000-0000-000000000000", "repo0001-0000-0000-0000-000000000000", 1
    )
    val issuePRE2 = Issue(
    "issu0002-0000-0000-0000-000000000000", "prog0003-0000-0000-0000-000000000000", "issue 2",
    null, "22/02/2022",
    "prog0001-0000-0000-0000-000000000000", "proj0002-0000-0000-0000-000000000000", "repo0001-0000-0000-0000-000000000000", 1
    )


    val issue = Issue(
            "12341234-aaaa-aaaa-AB12-1234567890zn",
            "12345678-1234-1234-1234-123456789012", "Issue 1",
            "awawawawawawawawawawawawawaawawawawawawawawawawawa",
            "26/05/2002",
            null, "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
            "22222222-xxxx-x1xx-xxxx-xxxxxx1xxxx1", 0
    )
    val issue2 = Issue(
            "12341234-aaaa-aaaa-AB12-1234567890zn",
            "12345678-1234-1234-1234-123456789012", "Issue 1",
            null,
            "26/05/2002",
            "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy,tttttttt-tttt-tttt-tttt-tttttttttttt",
            "22222222-xxxx-x1xx-xxxx-xxxxxx1xxxx1",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx", 1
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
    fun issueInsertWorksFine() {
        val result = IssueRepository().insert(issue)
        println("$result\n")
        assertEquals(issue, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun issueGetByIdWorksFine() {
        val result = IssueRepository().getById(issue.id)
        println("$result\n")
        assertEquals(issue.id, result.id)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun issueFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(issuePRE1.id)
        expectedList.add(issuePRE2.id)
        expectedList.add(issue.id)
        println(expectedList)
        val actualList = IssueRepository().findAll()
        println("$actualList\n")
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun issueUpdateWorksFine() {
        val result = IssueRepository().update(issue2)
        println(result)
        assertNotEquals(issue, result)
        assertEquals(issue2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun issueDeleteWorksFine() {
        val result = IssueRepository().delete(issue2)
        assertEquals(issue2, result)
    }
}