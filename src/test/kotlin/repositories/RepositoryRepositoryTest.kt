package repositories

import db.DBController
import model.Repository
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Repository Repository")
class RepositoryRepositoryTest {
    val repoPRE1 = Repository(
    "repo0001-0000-0000-0000-000000000000", "repo 1", "22/02/6006",
    "proj0001-0000-0000-0000-000000000000", "comm0001-0000-0000-0000-000000000000",
    "issu0001-0000-0000-0000-000000000000"
    );
    val repoPRE2 = Repository(
    "repo0002-0000-0000-0000-000000000000", "repo 2", "22/02/6006",
    "proj0002-0000-0000-0000-000000000000", "comm0002-0000-0000-0000-000000000000",
    "issu0002-0000-0000-0000-000000000000"
    );

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
    @DisplayName("Insert")
    @Order(1)
    fun repositoryInsertWorksFine() {
        val result = RepositoryRepository().insert(repo)
        println("$result\n")
        assertEquals(repo, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun repositoryGetByIdWorksFine() {
        val result = RepositoryRepository().getById(repo.id)
        println("$result\n")
        assertEquals(repo.id, result.id)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun repositoryFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(repoPRE1.id)
        expectedList.add(repoPRE2.id)
        expectedList.add(repo.id)
        println(expectedList)
        val actualList = RepositoryRepository().findAll()
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        println("$actualList\n")
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun repositoryUpdateWorksFine() {
        val result = RepositoryRepository().update(repo2)
        println(result)
        assertNotEquals(repo, result)
        assertEquals(repo2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun repositoryDeleteWorksFine() {
        val result = RepositoryRepository().delete(repo2)
        assertEquals(repo2, result)
    }
}