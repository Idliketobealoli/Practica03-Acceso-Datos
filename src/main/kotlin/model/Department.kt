package model

import javax.xml.bind.annotation.*

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
class Department () {
        @XmlAttribute
        lateinit var id: String
        lateinit var name: String
        lateinit var boss_id: String
        var budget: Double = 0.0
        var finishedProjects_ids: String? = null
        var developingProjects_ids: String? = null
        var anualBudget: Double = 0.0
        lateinit var bossHistory_ids: String

        constructor(
                id: String,
                name: String,
                boss: String,
                budget: Double,
                finishedProjects: String?,
                developingProjects: String?,
                anualBudget: Double,
                bossHistory: String
        ) : this() {
                this.id = id
                this.name = name
                this.boss_id = boss
                this.budget = budget
                this.finishedProjects_ids = finishedProjects
                this.developingProjects_ids = developingProjects
                this.anualBudget = anualBudget
                this.bossHistory_ids = bossHistory
        }
}