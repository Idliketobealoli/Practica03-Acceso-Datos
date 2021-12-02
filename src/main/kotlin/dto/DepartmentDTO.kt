package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Programmer
import model.Project
import javax.xml.bind.annotation.*

/**
 * Data transfer Object de Department. Preparado para ser sacado en formato XML y JSON.
 * @author Daniel Rodríguez
 */
@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
class DepartmentDTO () {
    @XmlAttribute
    lateinit var id: String
    lateinit var name: String
    lateinit var boss: Programmer
    var budget: Double = 0.0

    @XmlElementWrapper
    var finishedProjects: List<Project>? = null

    @XmlElementWrapper
    var developingProjects: List<Project>? = null
    var anualBudget: Double = 0.0

    @XmlElementWrapper
    lateinit var bossHistory: List<Programmer>

    constructor(
            id: String,
            name: String,
            boss: Programmer,
            budget: Double,
            finishedProjects: List<Project>?,
            developingProjects: List<Project>?,
            anualBudget: Double,
            bossHistory: List<Programmer>
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

    /**
     * De un string JSON lo convierte en un DepartmentDTO
     * @author Daniel Rodríguez
     * @param json String
     * @return DepartmentDTO
     */
    fun fromJSON(json : String) : DepartmentDTO? {
        return Gson().fromJson(json, DepartmentDTO::class.java)
    }

    /**
     * De un DepartmentDTO lo convierte en un string JSON
     * @author Daniel Rodríguez
     * @return String
     */
    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}