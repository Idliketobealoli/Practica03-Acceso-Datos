package model

data class Commit(
        var id: String,
        var title: String,
        var text: String?,
        var date: String,
        var repository_id: String,
        var project_id: String,
        var author_id: String,
        var issue_id: String
)
