package model

import javax.xml.bind.annotation.*

/**
 * Clase POKO Programmer cuyos campos irán a la base de datos.
 * @author Jaime Salcedo
 */
@XmlRootElement(name = "programmer")
@XmlAccessorType(XmlAccessType.FIELD)
class Programmer () {
        @XmlAttribute
        lateinit var id: String
        lateinit var name: String
        @XmlAttribute(name = "register_date")
        lateinit var registerDate: String
        lateinit var department_id: String
        var activeProjects_ids: String? = null
        var commits_ids: String? = null
        var issues_ids: String? = null
        var technologies: String? = null
        var salary: Double = 0.0

        @XmlAttribute(name = "department_boss")
        var isDepBoss: Int = 0

        @XmlAttribute(name = "project_manager")
        var isProjectManager: Int = 0

        @XmlAttribute(name = "active")
        var isActive: Int = 0

        constructor(
                id: String,
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