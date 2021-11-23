package model

import java.util.*

data class Issue(
        var id: String,
        var author_id: String,
        var title: String,
        var text: String,
        var date: Date,
        var programmers_ids: List<String>,
        var project_id: String,
        var repository_id: String,
        var isFinished: Boolean
)
