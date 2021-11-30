package dto

import Technology
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Department
import model.Programmer
import model.Repository
import javax.xml.bind.annotation.*

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
class ProjectDTO (
        @XmlAttribute
        var id: String,
        var department: Department,
        var projectManager: Programmer,
        var name: String,
        var budget: Double,
        @XmlAttribute(name = "start_date")
        var startDate: String,
        @XmlAttribute(name = "end_date")
        var endDate: String?,
        @XmlElementWrapper
        var technologies: List<Technology>?,
        var repository: Repository,
        @XmlAttribute(name = "finished")
        var isFinished: Boolean,
        @XmlElementWrapper
        var programmers: List<Programmer>?
) {
    fun fromJSON(json : String) : ProjectDTO? {
        return Gson().fromJson(json, ProjectDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}