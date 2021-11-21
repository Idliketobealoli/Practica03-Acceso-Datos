package model

import java.util.*

data class Programmer(
        var id: String,
        var name: String,
        var registerDate: Date,
        var department_id: String,
        var activeProjects_ids: List<String>,
        var commits_ids: List<String>,
        var issues_ids: List<String>,
        var technologies: List<String>,
        var salary: Double,
        var isDepBoss: Boolean,
        var isProjectManager: Boolean,
        var isActive: Boolean
)
