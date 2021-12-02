package repositories

import db.DBController
import model.Programmer
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Programmer Repository")
class ProgrammerRepositoryTest {
    val progPRE1 = Programmer(
    "prog0001-0000-0000-0000-000000000000", "prog", "26/05/2002",
    "depart01-0000-0000-0000-000000000000",
    "proj0001-0000-0000-0000-000000000000",
    "comm0001-0000-0000-0000-000000000000,comm0002-0000-0000-0000-000000000000,comm0003-0000-0000-0000-000000000000,comm0004-0000-0000-0000-000000000000",
    "issu0001-0000-0000-0000-000000000000",
    "JAVA,KOTLIN", 2.22, 0,0,1
    );
    val progPRE2 = Programmer(
    "prog0002-0000-0000-0000-000000000000", "boss", "06/05/2002",
    "depart01-0000-0000-0000-000000000000",
    null,
    null,
    null,
    "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    );
    val progPRE3 = Programmer(
    "prog0004-0000-0000-0000-000000000000", "boss2", "06/05/2002",
    "depart01-0000-0000-0000-000000000000",
    null,
    null,
    null,
    "JAVA,KOTLIN,CSHARP", -2.22, 1,0,0
    );
    val progPRE4 = Programmer(
    "prog0003-0000-0000-0000-000000000000", "manager", "26/08/2002",
    "depart01-0000-0000-0000-000000000000",
    "proj0001-0000-0000-0000-000000000000,proj0002-0000-0000-0000-000000000000,projDTO1-0000-0000-0000-000000000000",
    null,
    "issu0001-0000-0000-0000-000000000000",
    "KOTLIN", 2222.22, 0,1,0
    );


    val prog = Programmer(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Joseju", "26/05/2002",
            "12345678-1234-1234-1234-123456789012",
            "11111111-1111-1111-1111-111111111111,22222222-2222-2222-2222-222222222222",
            null, null, "JAVA,PHP", 6969.60, 0, 0, 1
    )
    val prog2 = Programmer(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "x", "26/05/2002",
            "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
            "22222222-2222-2222-2222-222222222222,11111111-1111-1111-1111-111111111111",
            null, null, "JAVA,PHP,KOTLIN", 1.60, 1, 0, 0
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
    fun programmerInsertWorksFine() {
        val result = ProgrammerRepository().insert(prog)
        println("$result\n")
        assertEquals(prog, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun programmerGetByIdWorksFine() {
        val result = ProgrammerRepository().getById(prog.id)
        println("$result\n")
        assertEquals(prog.id, result.id)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun programmerFindAllWorksFine() {
        val expectedList = ArrayList<String>()
        expectedList.add(progPRE1.id)
        expectedList.add(progPRE2.id)
        expectedList.add(progPRE3.id)
        expectedList.add(progPRE4.id)
        expectedList.add(prog.id)
        println(expectedList)
        val actualList = ProgrammerRepository().findAll()
        println("$actualList\n")
        val res = ArrayList<String>()
        actualList.forEach { x -> res.add(x.id) }
        assertEquals(expectedList, res)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun programmerUpdateWorksFine() {
        val result = ProgrammerRepository().update(prog2)
        println(result)
        assertNotEquals(prog, result)
        assertEquals(prog2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun programmerDeleteWorksFine() {
        val result = ProgrammerRepository().delete(prog2)
        assertEquals(prog2, result)
    }
}