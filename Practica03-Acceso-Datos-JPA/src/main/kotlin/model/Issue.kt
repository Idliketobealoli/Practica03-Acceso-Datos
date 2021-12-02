package model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.xml.bind.annotation.*

@Entity
@NamedQuery(name = "Issue.finAll", query = "SELECT d FROM Issue d")
@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
class Issue () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0
        @Column(nullable = false)
        lateinit var author_id: String
        @XmlAttribute
        @Column(nullable = false)
        lateinit var title: String
        @Column(nullable = false)
        var text: String? = null

        @Column(nullable = false)
        @XmlAttribute
        lateinit var date: String
        @Column(nullable = true)
        var programmers_ids: String? = null
        @Column(nullable = false)
        lateinit var project_id: String
        @Column(nullable = false)
        lateinit var repository_id: String
        @Column(nullable = false)
        @XmlAttribute(name = "finished")
        var isFinished: Int = 0

        constructor(
                id: Long,
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