package model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
class Issue () {
        @XmlAttribute
        lateinit var id: String
        lateinit var author_id: String
        @XmlAttribute
        lateinit var title: String
        var text: String? = null

        @XmlAttribute
        lateinit var date: String
        var programmers_ids: String? = null
        lateinit var project_id: String
        lateinit var repository_id: String
        @XmlAttribute(name = "finished")
        var isFinished: Int = 0

        constructor(
                id: String,
                author: String,
                title: String,
                text: String?,
                date: String,
                programmers: String?,
                project: String,
                repository: String,
                isFinished: Int = 0
        ) : this() {
                this.id = id
                this.author_id = author
                this.title = title
                this.text = text
                this.date = date
                this.programmers_ids = programmers
                this.project_id = project
                this.repository_id = repository
                this.isFinished = isFinished
        }
}