package model

data class Programmer(
        var id: String,
        var name: String,
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