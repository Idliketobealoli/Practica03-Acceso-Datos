package model

data class Repository(
        var id: String,
        var name: String,
        var creationDate: String,
        var project_id: String,
        var commits_ids: String?,
        var issues_ids: String?
)
