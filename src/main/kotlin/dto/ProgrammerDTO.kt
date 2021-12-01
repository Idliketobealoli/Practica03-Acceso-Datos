package dto

import Technology
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Commit
import model.Department
import model.Issue
import model.Project
import javax.xml.bind.annotation.*

@XmlRootElement(name = "programmer")
@XmlAccessorType(XmlAccessType.FIELD)
class ProgrammerDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var name: String
    @XmlAttribute(name = "register_date")
    lateinit var registerDate: String
    lateinit var department: Department
    @XmlElementWrapper(name = "active_projects")
    var activeProjects: List<Project>? = null

    @XmlElementWrapper
    var commits: List<Commit>? = null

    @XmlElementWrapper
    var issues: List<Issue>? = null

    @XmlElementWrapper
    var technologies: List<Technology>? = null
    var salary: Double = 0.0

    @XmlAttribute(name = "department_boss")
    var isDepBoss: Boolean = false

    @XmlAttribute(name = "project_manager")
    var isProjectManager: Boolean = false

    @XmlAttribute(name = "active")
    var isActive: Boolean = false

    constructor(
            id: String,
            name: String,
            registerDate: String,
            department: Department,
            activeProjects: List<Project>?,
            commits: List<Commit>?,
            issues: List<Issue>?,
            technologies: List<Technology>?,
            salary: Double = 0.0,
            isDepBoss: Boolean = false,
            isProjectManager: Boolean = false,
            isActive: Boolean = false
    ) : this() {
        this.id = id
        this.name = name
        this.registerDate = registerDate
        this.department = department
        this.activeProjects = activeProjects
        this.commits = commits
        this.issues = issues
        this.technologies = technologies
        this.salary = salary
        this.isDepBoss = isDepBoss
        this.isProjectManager = isProjectManager
        this.isActive = isActive
    }

    fun fromJSON(json: String) : ProgrammerDTO? {
        return Gson().fromJson(json, ProgrammerDTO::class.java)
    }

    fun toJSON() : String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }
}