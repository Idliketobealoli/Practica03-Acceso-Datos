package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "programmer")
@XmlAccessorType(XmlAccessType.FIELD)
data class Programmer(
        @XmlAttribute
        var id: String,
        var name: String,
        @XmlAttribute
        var registerDate: String,
        var department_id: String,
        var activeProjects_ids: String?,
        var commits_ids: String?,
        var issues_ids: String?,
        var technologies: String?,
        var salary: Double,
        var isDepBoss: Int,
        var isProjectManager: Int,
        var isActive: Int
)