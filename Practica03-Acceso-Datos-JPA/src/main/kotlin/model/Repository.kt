package model


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.xml.bind.annotation.*

/**
 * Clase POKO Repository cuyos campos irán a la base de datos.
 * @author Daniel Rodríguez
 */
@Entity
@NamedQuery(name = "Repository.finAll", query = "SELECT d FROM Repository d")
@XmlRootElement(name = "repository")
@XmlAccessorType(XmlAccessType.FIELD)
class Repository () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0
        @Column(nullable = false)
        lateinit var name: String
        @Column(nullable = false)
        @XmlAttribute(name = "creation_date")
        lateinit var creationDate: String
        @Column(nullable = false)
        lateinit var project_id: String
        @Column(nullable = true)
        var commits_ids: String? = null
        @Column(nullable = true)
        var issues_ids: String? = null

        constructor(
                id: Long,
                name: String,
                creationDate: String,
                project: String,
                commits: String?,
                issues: String?
        ) : this () {
                this.id = id
                this.name = name
                this.creationDate = creationDate
                this.project_id = project
                this.commits_ids = commits
                this.issues_ids = issues
        }
}
