package dto

import Technology
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.xml.bind.annotation.*

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
class ProjectDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var department: DepartmentDTO
    lateinit var projectManager: ProgrammerDTO
    lateinit var name: String
    var budget: Double = 0.0

    @XmlAttribute(name = "start_date")
    lateinit var startDate: String
    @XmlAttribute(name = "end_date")
    var endDate: String? = null

    @XmlElementWrapper
    var technologies: List<Technology>? = null
    lateinit var repository: RepositoryDTO
    @XmlAttribute(name = "finished")
    var isFinished: Boolean = false

    @XmlElementWrapper
    var programmers: List<ProgrammerDTO>? = null

    constructor(
            id: String,
            department: DepartmentDTO,
            projectManager: ProgrammerDTO,
            name: String,
            budget: Double = 0.0,
            startDate: String,
            endDate: String? = null,
            technologies: List<Technology>? = null,
            repository: RepositoryDTO,
            isFinished: Boolean = false,
            programmers: List<ProgrammerDTO>? = null
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

    fun fromJSON(json : String) : ProjectDTO? {
        return Gson().fromJson(json, ProjectDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}