package dto

import model.Issue
import model.Programmer
import model.Project
import model.Repository

class CommitDTO (
        var id: String,
        var title: String,
        var text: String?,
        var date: String,
        var repository: Repository,
        var project: Project,
        var author: Programmer,
        var issue: Issue
)