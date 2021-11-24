package model

import java.util.*

data class Project(
        var id: String,
        var department_id: String,
        var projectManager_id: String,
        var name: String,
        var budget: Double,
        var startDate: Date,
        var endDate: Date,
        var technologies: String,
        var repository_id: String,
        var isFinished: Boolean,
        var programmers_ids: String
)
