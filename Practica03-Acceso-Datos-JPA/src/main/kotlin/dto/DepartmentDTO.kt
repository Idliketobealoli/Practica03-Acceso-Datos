package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.persistence.*
import javax.xml.bind.annotation.*

@Entity
@NamedQuery(name = "Department.findAll", query = "SELECT c FROM DepartmentDTO c")
@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
class DepartmentDTO () {
    @get:Column(name = "id")
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Id
    @XmlAttribute
    lateinit var id: String

    @get:Column(name = "nombre")
    @get:Basic
    lateinit var name: String

    @OneToOne
    lateinit var boss: ProgrammerDTO

    @get:Column(name = "presupuesto")
    @get:Basic
    var budget: Double = 0.0

    @OneToMany
    @XmlElementWrapper
    var finishedProjects: List<ProjectDTO>? = null

    @OneToMany
    @XmlElementWrapper
    var developingProjects: List<ProjectDTO>? = null

    @get:Column(name = "presupuesto_anual")
    @get:Basic
    var anualBudget: Double = 0.0

    @get:JoinColumn(name = "programmer_id", referencedColumnName = "id", nullable = false)
    @ManyToMany
    @XmlElementWrapper
    lateinit var bossHistory: List<ProgrammerDTO>

    constructor(
            id: String,
            name: String,
            boss: ProgrammerDTO,
            budget: Double,
            finishedProjects: List<ProjectDTO>?,
            developingProjects: List<ProjectDTO>?,
            anualBudget: Double,
            bossHistory: List<ProgrammerDTO>
    ) : this() {
        this.id = id
        this.name = name
        this.boss = boss
        this.budget = budget
        this.finishedProjects = finishedProjects
        this.developingProjects = developingProjects
        this.anualBudget = anualBudget
        this.bossHistory = bossHistory
    }

    fun fromJSON(json : String) : DepartmentDTO? {
        return Gson().fromJson(json, DepartmentDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}