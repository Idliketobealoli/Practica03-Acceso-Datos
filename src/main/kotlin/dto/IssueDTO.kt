package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Programmer
import model.Project
import model.Repository
import javax.xml.bind.annotation.*

/**
 * Data transfer Object de Issue. Preparado para ser sacado en formato XML y JSON.
 * @author Daniel Rodríguez
 */
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

    /**
     * De un string JSON lo convierte en un IssueDTO
     * @author Daniel Rodríguez
     * @param json String
     * @return IssueDTO
     */
    fun fromJSON(json : String) : IssueDTO? {
        return Gson().fromJson(json, IssueDTO::class.java)
    }

    /**
     * De un IssueDTO lo convierte en un string JSON
     * @author Daniel Rodríguez
     * @return String
     */
    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}