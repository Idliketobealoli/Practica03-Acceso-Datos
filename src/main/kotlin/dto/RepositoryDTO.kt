package dto

import model.Commit
import model.Issue
import model.Project

class RepositoryDTO (
        var id: String,
        var name: String,
        var creationDate: String,
        var project: Project,
        var commits: List<Commit>?,
        var issues: List<Issue>?
)