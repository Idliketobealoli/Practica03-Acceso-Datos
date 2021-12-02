import dto.*
import java.io.File
import java.nio.file.Files
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

/**
 * Clase encargada de pasar el DTO introducido a xml.
 * @author Jaime Salcedo
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

    /**
     * Este metodo se encarga de pasar el DepartmentDTO que le pasemos a xml
     * @param x DepartmentDTO
     * @author Jaime Salcedo
     */
    fun departmentToXML(x: DepartmentDTO) {
        val jaxbContext = JAXBContext.newInstance(DepartmentDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo se encarga de pasar el ProgrammerDTO que le pasemos a xml
     * @param x ProgrammerDTO
     * @author Jaime Salcedo
     */
    fun programmerToXML(x: ProgrammerDTO) {
        val jaxbContext = JAXBContext.newInstance(ProgrammerDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo se encarga de pasar el CommitDTO que le pasemos a xml
     * @param x CommitDTO
     * @author Jaime Salcedo
     */
    fun commitToXML(x: CommitDTO) {
        val jaxbContext = JAXBContext.newInstance(CommitDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo se encarga de pasar el ProjectDTO que le pasemos a xml
     * @param x ProjectDTO
     * @author Jaime Salcedo
     */
    fun projectToXML(x: ProjectDTO) {
        val jaxbContext = JAXBContext.newInstance(ProjectDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo se encarga de pasar el IssueDTO que le pasemos a xml
     * @param x IssueDTO
     * @author Jaime Salcedo
     */
    fun issueToXML(x: IssueDTO) {
        val jaxbContext = JAXBContext.newInstance(IssueDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo se encarga de pasar el RepositoryDTO que le pasemos a xml
     * @param x RepositoryDTO
     * @author Jaime Salcedo
     */
    fun repositoryToXML(x: RepositoryDTO) {
        val jaxbContext = JAXBContext.newInstance(RepositoryDTO::class.java)
        printXML(jaxbContext, x)
    }

    /**
     * Este metodo coge el context que le pasas y crea un archivo formateado en un path, lee el archivo línea a línea
     * y lo printea
     * @param jaxbContext JAXBContext
     * @param x: Any
     * @author Jaime Salcedo
     */
    private fun printXML(jaxbContext: JAXBContext, x: Any) {
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.marshal(x, file)
        val result = Files.readAllLines(file.toPath())
        result.forEach { y -> println(y) }
    }
}