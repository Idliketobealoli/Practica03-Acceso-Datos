package model

data class Issue(
        var id: String,
        var author_id: String,
        var title: String,
        var text: String?,
        var date: String,
        var programmers_ids: String?,
        var project_id: String,
        var repository_id: String,
        var isFinished: Int
)
