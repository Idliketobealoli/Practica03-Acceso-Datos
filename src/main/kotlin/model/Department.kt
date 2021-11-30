package model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
data class Department(
        @XmlAttribute
        var id: String,
        var name: String,
        var boss_id: String,
        @XmlAttribute
        var budget: Double,
        var finishedProjects_ids: String?,
        var developingProjects_ids: String?,
        @XmlTransient
        var anualBudget: Double,
        var bossHistory_ids: String
)
