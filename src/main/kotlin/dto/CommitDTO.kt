package dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Issue
import model.Programmer
import model.Project
import model.Repository
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
class CommitDTO (
        @XmlAttribute
        var id: String,
        @XmlAttribute
        var title: String,
        var text: String?,
        @XmlAttribute
        var date: String,
        var repository: Repository,
        var project: Project,
        var author: Programmer,
        var issue: Issue
) {
    fun fromJSON(json : String) : CommitDTO? {
        return Gson().fromJson(json, CommitDTO::class.java)
    }

    fun toJSON() : String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}