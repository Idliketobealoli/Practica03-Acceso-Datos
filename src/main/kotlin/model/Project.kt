package model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
data class Project(
        @XmlAttribute
        var id: String,
        var department_id: String,
        var projectManager_id: String,
        var name: String,
        @XmlAttribute
        var budget: Double,
        @XmlAttribute
        var startDate: String,
        @XmlAttribute
        var endDate: String?,
        var technologies: String?,
        var repository_id: String,
        @XmlAttribute
        var isFinished: Int,
        @XmlTransient
        var programmers_ids: String?
)
