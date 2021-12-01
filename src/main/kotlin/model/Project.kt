package model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
class Project () {
        @XmlAttribute
        lateinit var id: String
        lateinit var department_id: String
        lateinit var projectManager_id: String
        lateinit var name: String
        var budget: Double = 0.0
        @XmlAttribute(name = "start_date")
        lateinit var startDate: String
        @XmlAttribute(name = "end_date")
        var endDate: String? = null
        var technologies: String? = null
        lateinit var repository_id: String
        @XmlAttribute(name = "finished")
        var isFinished: Int = 0
        var programmers_ids: String? = null

        constructor(
                id: String,
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