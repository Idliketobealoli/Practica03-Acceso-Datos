package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.persistence.*
import javax.xml.bind.annotation.*

@Entity
@NamedQuery(name = "Issue.findAll", query = "SELECT c FROM IssueDTO c")
@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
class IssueDTO () {
    @get:Column(name = "id")
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Id
    @XmlAttribute
    lateinit var id: String

    @OneToOne
    lateinit var author: ProgrammerDTO

    @get:Column(name = "titulo")
    @get:Basic
    @XmlAttribute
    lateinit var title: String

    @get:Column(name = "texto")
    @get:Basic
    var text: String? = null

    @get:Column(name = "fecha")
    @get:Basic
    @XmlAttribute
    lateinit var date: String

    @get:JoinColumn(name = "programmer_id", referencedColumnName = "id", nullable = true)
    @ManyToMany
    @XmlElementWrapper
    var programmers: List<ProgrammerDTO>? = null

    @OneToOne
    lateinit var project: ProjectDTO

    @get:JoinColumn(name = "repository_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    lateinit var repository: RepositoryDTO
    @XmlAttribute(name = "finished")
    var isFinished: Boolean = false

    constructor(
            id: String,
            author: ProgrammerDTO,
            title: String,
            text: String?,
            date: String,
            programmers: List<ProgrammerDTO>?,
            project: ProjectDTO,
            repository: RepositoryDTO,
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