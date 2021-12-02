package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.persistence.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

/**
 * Data transfer Object de Commit. Preparado para ser sacado en formato XML y JSON.
 */
@Entity
@NamedQuery(name = "Commit.findAll", query = "SELECT c FROM CommitDTO c")
@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
class CommitDTO () {
    @get:Column(name = "id")
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Id
    @XmlAttribute
    lateinit var id: String

    @get:Column
    @get:Basic
    @XmlAttribute
    lateinit var title: String

    @get:Column
    @get:Basic
    var text: String? = null

    @get:Column
    @get:Basic
    @XmlAttribute
    lateinit var date: String

    @get:JoinColumn(name = "repository_id", referencedColumnName = "id", nullable = false)
    @get:ManyToOne
    lateinit var repository: RepositoryDTO

    @get:OneToOne
    lateinit var project: ProjectDTO

    @get:JoinColumn(name = "programmer_id", referencedColumnName = "id", nullable = false)
    @get:ManyToOne
    lateinit var author: ProgrammerDTO

    @get:OneToOne
    lateinit var issue: IssueDTO

    constructor(
            id: String,
            title: String,
            text: String? = null,
            date: String,
            repository: RepositoryDTO,
            project: ProjectDTO,
            author: ProgrammerDTO,
            issue: IssueDTO
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

    /**
     * De un string JSON lo convierte en un CommitDTO
     */
    fun fromJSON(json : String) : CommitDTO? {
        return Gson().fromJson(json, CommitDTO::class.java)
    }

    /**
     * De un CommitDTO lo convierte en un string JSON
     */
    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}