package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Programmer
import model.Project
import model.Repository
import javax.xml.bind.annotation.*

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
class IssueDTO (
        @XmlAttribute
        var id: String,
        var author: Programmer,
        @XmlAttribute
        var title: String,
        var text: String?,
        @XmlAttribute
        var date: String,
        @XmlElementWrapper
        var programmers: List<Programmer>?,
        var project: Project,
        var repository: Repository,
        @XmlAttribute(name = "finished")
        var isFinished: Boolean
) {
    fun fromJSON(json : String) : IssueDTO? {
        return Gson().fromJson(json, IssueDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}