package repositories

import db.DBController
import model.Programmer
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProgrammerRepositoryTest {
    val prog = Programmer(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Joseju", "26/05/2002",
            "12345678-1234-1234-1234-123456789012",
            "11111111-1111-1111-1111-111111111111,22222222-2222-2222-2222-222222222222",
            null, null, "JAVA,PHP", 6969.60, 0, 0, 1
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
    fun programmerInsertWorksFine() {
        val result = ProgrammerRepository().insert(prog)
        println("$result\n")
        assertEquals(prog, result)
    }

    @Test
    @Order(2)
    fun programmerGetByIdWorksFine() {
        val result = ProgrammerRepository().getById(prog.id)
        println("$result\n")
        assertEquals(prog, result)
    }

    @Test
    @Order(3)
    fun programmerFindAllWorksFine() {
        val expectedList = listOf(prog)
        println(expectedList)
        val actualList = ProgrammerRepository().findAll()
        println("$actualList\n")
        assertEquals(expectedList, actualList)
    }

    @Test
    @Order(4)
    fun programmerUpdateWorksFine() {
        val prog2 = Programmer(
                "12341234-aaaa-aaaa-AB12-1234567890zn", "x", "26/05/2002",
                "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                "22222222-2222-2222-2222-222222222222,11111111-1111-1111-1111-111111111111",
                null, null, "JAVA,PHP,KOTLIN", 1.60, 1, 0, 0
        )
        val result = ProgrammerRepository().update(prog2)
        println(result)
        assertNotEquals(prog, result)
        assertEquals(prog2, result)
    }

    @Test
    @Order(5)
    fun programmerDeleteWorksFine() {
        val result = ProgrammerRepository().delete(prog)
        assertEquals(prog, result)
    }
}