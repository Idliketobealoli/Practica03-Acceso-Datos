package dto

import Technology
import model.Department
import model.Programmer
import model.Repository

class ProjectDTO (
        var id: String,
        var department: Department,
        var projectManager: Programmer,
        var name: String,
        var budget: Double,
        var startDate: String,
        var endDate: String?,
        var technologies: List<Technology>?,
        var repository: Repository,
        var isFinished: Boolean,
        var programmers: List<Programmer>?
)