package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

/**
 * Clase POKO cuyos campos irán a la base de datos.
 */
@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
class Commit () {
        @XmlAttribute
        lateinit var id: String
        @XmlAttribute
        lateinit var title: String
        var text: String? = null

        @XmlAttribute
        lateinit var date: String
        lateinit var repository_id: String
        lateinit var project_id: String
        lateinit var author_id: String
        lateinit var issue_id: String

        constructor(
                id: String,
                title: String,
                text: String? = null,
                date: String,
                repository: String,
                project: String,
                author: String,
                issue: String
        ) : this() {
                this.id = id
                this.title = title
                this.text = text
                this.date = date
                this.repository_id = repository
                this.project_id = project
                this.author_id = author
                this.issue_id = issue
        }
}
