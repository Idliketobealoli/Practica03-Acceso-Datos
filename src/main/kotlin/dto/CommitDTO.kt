package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Issue
import model.Programmer
import model.Project
import model.Repository
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
class CommitDTO () {
    @XmlAttribute
    lateinit var id: String
    @XmlAttribute
    lateinit var title: String
    var text: String? = null

    @XmlAttribute
    lateinit var date: String
    lateinit var repository: Repository
    lateinit var project: Project
    lateinit var author: Programmer
    lateinit var issue: Issue

    constructor(
            id: String,
            title: String,
            text: String? = null,
            date: String,
            repository: Repository,
            project: Project,
            author: Programmer,
            issue: Issue
    ) : this() {
        this.id = id
        this.title = title
        this.text = text
        this.date = date
        this.repository = repository
        this.project = project
        this.author = author
        this.issue = issue
    }
    fun fromJSON(json : String) : CommitDTO? {
        return Gson().fromJson(json, CommitDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}