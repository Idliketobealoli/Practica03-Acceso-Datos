import dto.CommitDTO
import dto.DepartmentDTO
import dto.ProgrammerDTO
import java.io.File
import java.nio.file.Files
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

object Jaxb {
    private val fileDirectory = File("${System.getProperty("user.dir")}${ File.separator}temporalFiles")
    val file = File("${System.getProperty("user.dir")}${ File.separator}temporalFiles${File.separator}temp")
    init {
        if (!fileDirectory.exists())
        {
            fileDirectory.mkdirs()
        }
    }

    fun departmentToXML(x: DepartmentDTO) {
        //val jaxbContext = JAXBContext.newInstance(DepartmentDTO::class.java)
        //printXML(jaxbContext, x)
    }

    fun programmerToXML(x: ProgrammerDTO) {
        //val jaxbContext = JAXBContext.newInstance(ProgrammerDTO::class.java)
        //printXML(jaxbContext, x)
    }

    private fun printXML(jaxbContext: JAXBContext, x: Any) {
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.marshal(x, file)
        val result = Files.readAllLines(file.toPath())
        result.forEach { y -> println(y) }
    }

    fun commitToXML(x: CommitDTO) {

    }
}