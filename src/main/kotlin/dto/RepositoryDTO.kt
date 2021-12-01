package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Commit
import model.Issue
import model.Project
import javax.xml.bind.annotation.*

@XmlRootElement(name = "repository")
@XmlAccessorType(XmlAccessType.FIELD)
class RepositoryDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var name: String
    @XmlAttribute(name = "creation_date")
    lateinit var creationDate: String
    lateinit var project: Project
    @XmlElementWrapper
    var commits: List<Commit>? = null

    @XmlElementWrapper
    var issues: List<Issue>? = null

    constructor(
            id: String,
            name: String,
            creationDate: String,
            project: Project,
            commits: List<Commit>?,
            issues: List<Issue>?
    ) : this () {
        this.id = id
        this.name = name
        this.creationDate = creationDate
        this.project = project
        this.commits = commits
        this.issues = issues
    }

    fun fromJSON(json : String) : RepositoryDTO? {
        return Gson().fromJson(json, RepositoryDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}