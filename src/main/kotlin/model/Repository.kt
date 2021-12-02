package model


import javax.xml.bind.annotation.*

/**
 * Clase POKO Repository cuyos campos irán a la base de datos.
 * @author Daniel Rodríguez
 */
@XmlRootElement(name = "repository")
@XmlAccessorType(XmlAccessType.FIELD)
class Repository () {
        @XmlAttribute
        lateinit var id: String
        lateinit var name: String
        @XmlAttribute(name = "creation_date")
        lateinit var creationDate: String
        lateinit var project_id: String
        var commits_ids: String? = null
        var issues_ids: String? = null

        constructor(
                id: String,
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
