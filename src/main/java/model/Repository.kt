package model

import java.util.*

data class Repository(
        var id: String,
        var name: String,
        var creationDate: Date,
        var project_id: String,
        var commits_ids: List<String>,
        var issues_ids: List<String>
)
