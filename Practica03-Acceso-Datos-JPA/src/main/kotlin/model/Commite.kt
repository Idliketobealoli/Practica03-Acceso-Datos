package model

import javax.persistence.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

/**
 * Clase POKO Commite cuyos campos irán a la base de datos.
 * @author Jaime Salcedo
 */
@Entity
@NamedQuery(name = "Commite.finAll", query = "SELECT d FROM Commite d")
@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
class Commite () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0

        @Column(nullable = false)
        @XmlAttribute
        lateinit var title: String
        @Column(nullable = true)
        var text: String? = null

        @Column(nullable = false)
        @XmlAttribute
        lateinit var date: String
        @Column(nullable = false)
        lateinit var repository_id: String
        @Column(nullable = false)
        lateinit var project_id: String
        @Column(nullable = false)
        lateinit var author_id: String
        @Column(nullable = false)
        lateinit var issue_id: String

        constructor(
                id: Long,
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
