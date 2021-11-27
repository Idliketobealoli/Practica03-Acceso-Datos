package model

data class Project(
        var id: String,
        var department_id: String,
        var projectManager_id: String,
        var name: String,
        var budget: Double,
        var startDate: String,
        var endDate: String?,
        var technologies: String?,
        var repository_id: String,
        var isFinished: Int,
        var programmers_ids: String?
)
