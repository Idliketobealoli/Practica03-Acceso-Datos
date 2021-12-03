package model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.xml.bind.annotation.*

/**
 * Clase POKO Department cuyos campos irán a la base de datos.
 * @author Jaime Salcedo
 */
@Entity
@NamedQuery(name = "Department.finAll", query = "SELECT d FROM Department d")
@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
class Department () {
        @Id
        @Column(nullable = false)
        @XmlAttribute
        var id: Long = 0
        @Column(nullable = false)
        lateinit var name: String
        @Column(nullable = false)
        lateinit var boss_id: String
        @Column(nullable = false)
        var budget: Double = 0.0
        @Column(nullable = true)
        var finishedProjects_ids: String? = null
        @Column(nullable = true)
        var developingProjects_ids: String? = null
        @Column(nullable = false)
        var anualBudget: Double = 0.0
        @Column(nullable = false)
        lateinit var bossHistory_ids: String

        constructor(
                id: Long,
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