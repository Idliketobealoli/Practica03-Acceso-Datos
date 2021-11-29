package repositories

import db.DBController
import model.Department
import org.junit.jupiter.api.*
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("Department Repository")
class DepartmentRepositoryTest {
    val dep = Department(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Department 1",
            "12345678-1234-1234-1234-123456789012",
            10.0,
            "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy,tttttttt-tttt-tttt-tttt-tttttttttttt",
            null, 6969.69,
            "22222222-xxxx-x1xx-xxxx-xxxxxx1xxxx1"
    )
    val dep2 = Department(
            "12341234-aaaa-aaaa-AB12-1234567890zn", "Department 2",
            "oooooooo-oooo-oooo-oooo-oooooooooooo",
            -90.0,
            null,
            "yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyyyy,tttttttt-tttt-tttt-tttt-tttttttttttt",
            3985.67,
            "22222222-xxxx-x1xx-xxxx-xxxxxx1xxxx1,xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
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
        val result = DepartmentRepository().insert(dep)
        println("$result\n")
        assertEquals(dep, result)
    }

    @Test
    @DisplayName("Get by id")
    @Order(2)
    fun departmentGetByIdWorksFine() {
        val result = DepartmentRepository().getById(dep.id)
        println("$result\n")
        assertEquals(dep, result)
    }

    @Test
    @DisplayName("Find all")
    @Order(3)
    fun issueFindAllWorksFine() {
        val expectedList = listOf(dep)
        println(expectedList)
        val actualList = DepartmentRepository().findAll()
        println("$actualList\n")
        assertEquals(expectedList, actualList)
    }

    @Test
    @DisplayName("Update")
    @Order(4)
    fun issueUpdateWorksFine() {
        val result = DepartmentRepository().update(dep2)
        println(result)
        assertNotEquals(dep, result)
        assertEquals(dep2, result)
    }

    @Test
    @DisplayName("Delete")
    @Order(5)
    fun issueDeleteWorksFine() {
        val result = DepartmentRepository().delete(dep2)
        assertEquals(dep2, result)
    }
}