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
        var anualBudget: Double?,
        @XmlElementWrapper
        var bossHistory: List<Programmer>?
) {
    fun fromJSON(json : String) : DepartmentDTO? {
        return Gson().fromJson(json, DepartmentDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}