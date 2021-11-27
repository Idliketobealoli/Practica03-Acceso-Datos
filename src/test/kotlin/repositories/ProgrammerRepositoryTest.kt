package repositories

import db.DBController
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProgrammerRepositoryTest {
    @BeforeEach
    fun connect() {
        DBController.open()
    }
    @AfterEach
    fun disconnect() {
        DBController.close()
    }

    @Test
    fun programmerGetByIdWorksFine() {

    }
}