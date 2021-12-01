package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Programmer
import model.Project
import javax.xml.bind.annotation.*

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
class DepartmentDTO (
        @XmlAttribute
        var id: String,
        var name: String,
        var boss: Programmer,
        var budget: Double,
        @XmlElementWrapper
        var finishedProjects: List<Project>?,
        @XmlElementWrapper
        var developingProjects: List<Project>?,
        var anualBudget: Double,
        @XmlElementWrapper
        var bossHistory: List<Programmer>
) {
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

    fun fromJSON(json : String) : DepartmentDTO? {
        return Gson().fromJson(json, DepartmentDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}