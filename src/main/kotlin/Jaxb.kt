import dto.*
import java.io.File
import java.nio.file.Files
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

/**
 * Clase encargada de pasar el DTO introducido a xml.
 */
object Jaxb {
    private val fileDirectory = File("${System.getProperty("user.dir")}${File.separator}temporalFiles")
    val file = File("${System.getProperty("user.dir")}${File.separator}temporalFiles${File.separator}temp.txt")

    /**
     * Si el directorio donde se va a almacenar el xml a mostrar no existe, lo crea para que vaya correcto todo
     */
    init {
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs()
        }
    }

    fun departmentToXML(x: DepartmentDTO) {
        val jaxbContext = JAXBContext.newInstance(DepartmentDTO::class.java)
        printXML(jaxbContext, x)
    }

    fun programmerToXML(x: ProgrammerDTO) {
        val jaxbContext = JAXBContext.newInstance(ProgrammerDTO::class.java)
        printXML(jaxbContext, x)
    }

    fun commitToXML(x: CommitDTO) {
        val jaxbContext = JAXBContext.newInstance(CommitDTO::class.java)
        printXML(jaxbContext, x)
    }

    fun projectToXML(x: ProjectDTO) {
        val jaxbContext = JAXBContext.newInstance(ProjectDTO::class.java)
        printXML(jaxbContext, x)
    }

    fun issueToXML(x: IssueDTO) {
        val jaxbContext = JAXBContext.newInstance(IssueDTO::class.java)
        printXML(jaxbContext, x)
    }

    fun repositoryToXML(x: RepositoryDTO) {
        val jaxbContext = JAXBContext.newInstance(RepositoryDTO::class.java)
        printXML(jaxbContext, x)
    }

    private fun printXML(jaxbContext: JAXBContext, x: Any) {
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.marshal(x, file)
        val result = Files.readAllLines(file.toPath())
        result.forEach { y -> println(y) }
    }
}