package dto

import model.Programmer
import model.Project

class DepartmentDTO (
        var id: String,
        var name: String,
        var boss: Programmer,
        var budget: Double,
        var finishedProjects: List<Project>?,
        var developingProjects: List<Project>?,
        var anualBudget: Double?,
        var bossHistory: List<Programmer>?
)