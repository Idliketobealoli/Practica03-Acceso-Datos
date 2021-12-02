package dto

import Technology
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Department
import model.Programmer
import model.Repository
import javax.xml.bind.annotation.*

/**
 * Data transfer Object de Project. Preparado para ser sacado en formato XML y JSON.
 * @author Jaime Salcedo
 */
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
class ProjectDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var department: Department
    lateinit var projectManager: Programmer
    lateinit var name: String
    var budget: Double = 0.0

    @XmlAttribute(name = "start_date")
    lateinit var startDate: String
    @XmlAttribute(name = "end_date")
    var endDate: String? = null

    @XmlElementWrapper
    var technologies: List<Technology>? = null
    lateinit var repository: Repository
    @XmlAttribute(name = "finished")
    var isFinished: Boolean = false

    @XmlElementWrapper
    var programmers: List<Programmer>? = null

    constructor(
            id: String,
            department: Department,
            projectManager: Programmer,
            name: String,
            budget: Double = 0.0,
            startDate: String,
            endDate: String? = null,
            technologies: List<Technology>? = null,
            repository: Repository,
            isFinished: Boolean = false,
            programmers: List<Programmer>? = null
    ) : this() {
        this.id = id
        this.department = department
        this.projectManager = projectManager
        this.name = name
        this.budget = budget
        this.startDate = startDate
        this.endDate = endDate
        this.technologies = technologies
        this.repository = repository
        this.isFinished = isFinished
        this.programmers = programmers
    }

    /**
     * De un string JSON lo convierte en un ProjectDTO
     * @author Jaime Salcedo
     * @param json String
     * @return ProjectDTO
     */
    fun fromJSON(json : String) : ProjectDTO? {
        return Gson().fromJson(json, ProjectDTO::class.java)
    }

    /**
     * De un ProjectDTO lo convierte en un string JSON
     * @author Jaime Salcedo
     * @return String
     */
    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}