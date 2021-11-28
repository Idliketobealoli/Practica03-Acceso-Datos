package repositories

import db.DBController
import model.Repository
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class RepositoryRepositoryTest {
    val repo = Repository(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "repo 1", "26/05/2002",
            "12345678-1234-1234-1234-123456789012",
            "11111111-1111-1111-1111-111111111111,22222222-2222-2222-2222-222222222222",
            null
    )
    val repo2 = Repository(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "x", "26/05/2002",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
            null,
            "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa,ssssssss-ssss-ssss-ssss-ssssssssssss"
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
    @Order(1)
    fun repositoryInsertWorksFine() {
        val result = RepositoryRepository().insert(repo)
        println("$result\n")
        assertEquals(repo, result)
    }

    @Test
    @Order(2)
    fun repositoryGetByIdWorksFine() {
        val result = RepositoryRepository().getById(repo.id)
        println("$result\n")
        assertEquals(repo, result)
    }

    @Test
    @Order(3)
    fun repositoryFindAllWorksFine() {
        val expectedList = listOf(repo)
        println(expectedList)
        val actualList = RepositoryRepository().findAll()
        println("$actualList\n")
        assertEquals(expectedList, actualList)
    }

    @Test
    @Order(4)
    fun repositoryUpdateWorksFine() {
        val result = RepositoryRepository().update(repo2)
        println(result)
        assertNotEquals(repo, result)
        assertEquals(repo2, result)
    }

    @Test
    @Order(5)
    fun repositoryDeleteWorksFine() {
        val result = RepositoryRepository().delete(repo2)
        assertEquals(repo2, result)
    }
}