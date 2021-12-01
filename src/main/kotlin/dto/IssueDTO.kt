package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Programmer
import model.Project
import model.Repository
import javax.xml.bind.annotation.*

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
class IssueDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var author: Programmer
    @XmlAttribute
    lateinit var title: String
    var text: String? = null

    @XmlAttribute
    lateinit var date: String
    @XmlElementWrapper
    var programmers: List<Programmer>? = null
    lateinit var project: Project
    lateinit var repository: Repository
    @XmlAttribute(name = "finished")
    var isFinished: Boolean = false

    constructor(
            id: String,
            author: Programmer,
            title: String,
            text: String?,
            date: String,
            programmers: List<Programmer>?,
            project: Project,
            repository: Repository,
            isFinished: Boolean = false
    ) : this() {
        this.id = id
        this.author = author
        this.title = title
        this.text = text
        this.date = date
        this.programmers = programmers
        this.project = project
        this.repository = repository
        this.isFinished = isFinished
    }

    fun fromJSON(json : String) : IssueDTO? {
        return Gson().fromJson(json, IssueDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}