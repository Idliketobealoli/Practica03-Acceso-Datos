package dto

import model.Programmer
import model.Project
import model.Repository

class IssueDTO (
        var id: String,
        var author: Programmer,
        var title: String,
        var text: String?,
        var date: String,
        var programmers: List<Programmer>?,
        var project: Project,
        var repository: Repository,
        var isFinished: Boolean
)