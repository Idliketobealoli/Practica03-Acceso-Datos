package model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.xml.bind.annotation.*

/**
 * Clase POKO Project cuyos campos irán a la base de datos.
 * @author Daniel Rodríguez
 */
@Entity
@NamedQuery(name = "Project.finAll", query = "SELECT d FROM Project d")
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
class Project () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0
        @Column(nullable = false)
        lateinit var department_id: String
        @Column(nullable = false)
        lateinit var projectManager_id: String
        @Column(nullable = false)
        lateinit var name: String
        @Column(nullable = false)
        var budget: Double = 0.0
        @Column(nullable = false)
        @XmlAttribute(name = "start_date")
        lateinit var startDate: String
        @Column(nullable = true)
        @XmlAttribute(name = "end_date")
        var endDate: String? = null
        @Column(nullable = true)
        var technologies: String? = null
        @Column(nullable = false)
        lateinit var repository_id: String
        @XmlAttribute(name = "finished")
        @Column(nullable = false)
        var isFinished: Int = 0
        @Column(nullable = true)
        var programmers_ids: String? = null

        constructor(
                id: Long,
                department: String,
                projectManager: String,
                name: String,
                budget: Double = 0.0,
                startDate: String,
                endDate: String? = null,
                technologies: String? = null,
                repository: String,
                isFinished: Int = 0,
                programmers: String? = null
        ) : this() {
                this.id = id
                this.department_id = department
                this.projectManager_id = projectManager
                this.name = name
                this.budget = budget
                this.startDate = startDate
                this.endDate = endDate
                this.technologies = technologies
                this.repository_id = repository
                this.isFinished = isFinished
                this.programmers_ids = programmers
        }
}