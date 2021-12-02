package model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.xml.bind.annotation.*

@Entity
@NamedQuery(name = "Programmer.finAll", query = "SELECT d FROM Programmer d")
@XmlRootElement(name = "programmer")
@XmlAccessorType(XmlAccessType.FIELD)
class Programmer () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0
        @Column(nullable = false)
        lateinit var name: String
        @Column(nullable = false)
        @XmlAttribute(name = "register_date")
        lateinit var registerDate: String
        @Column(nullable = false)
        lateinit var department_id: String
        @Column(nullable = true)
        var activeProjects_ids: String? = null
        @Column(nullable = true)
        var commits_ids: String? = null
        @Column(nullable = true)
        var issues_ids: String? = null
        @Column(nullable = true)
        var technologies: String? = null
        @Column(nullable = false)
        var salary: Double = 0.0

        @Column(nullable = false)
        @XmlAttribute(name = "department_boss")
        var isDepBoss: Int = 0

        @Column(nullable = false)
        @XmlAttribute(name = "project_manager")
        var isProjectManager: Int = 0

        @Column(nullable = false)
        @XmlAttribute(name = "active")
        var isActive: Int = 0

        constructor(
                id: Long,
                name: String,
                registerDate: String,
                department: String,
                activeProjects: String?,
                commits: String?,
                issues: String?,
                technologies: String?,
                salary: Double = 0.0,
                isDepBoss: Int = 0,
                isProjectManager: Int = 0,
                isActive: Int = 0
        ) : this() {
                this.id = id
                this.name = name
                this.registerDate = registerDate
                this.department_id = department
                this.activeProjects_ids = activeProjects
                this.commits_ids = commits
                this.issues_ids = issues
                this.technologies = technologies
                this.salary = salary
                this.isDepBoss = isDepBoss
                this.isProjectManager = isProjectManager
                this.isActive = isActive
        }
}