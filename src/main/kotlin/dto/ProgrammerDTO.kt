package dto

import Technology
import model.Commit
import model.Department
import model.Issue
import model.Project

class ProgrammerDTO /*private constructor*/(
        var id: String,
        var name: String,
        var registerDate: String,
        var department: Department,
        var activeProjects: List<Project>?,
        var commits: List<Commit>?,
        var issues: List<Issue>?,
        var technologies: List<Technology>?,
        var salary: Double,
        var isDepBoss: Boolean,
        var isProjectManager: Boolean,
        var isActive: Boolean
) /*{
    data class Builder (
            var id: String,
            var name: String,
            var registerDate: String,
            var department_id: String,
            var department: Department,
            var activeProjects_ids: String? = null,
            var activeProjects: List<Project>? = null,
            var commits_ids: String? = null,
            var commits: List<Commit>? = null,
            var issues_ids: String? = null,
            var issues: List<Issue>? = null,
            var technologies: List<Technology>? = null,
            var salary: Double,
            var isDepBoss: Boolean,
            var isProjectManager: Boolean,
            var isActive: Boolean
            ) {
        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun registerDate(rd: String) = apply { this.registerDate = rd }
        fun department_id(: ) = apply { this. =  }
        fun department(: ) = apply { this. =  }
        fun activeProjects_ids(: ) = apply { this. =  }
        fun activeProjects(: ) = apply { this. =  }
        fun commits_ids(: ) = apply { this. = }
        fun commits(: ) = apply { this. =  }
        fun issues_ids(: ) = apply { this. =  }
        fun issues(: ) = apply { this. =  }
        fun technologies(: ) = apply { this. =  }
        fun salary(: ) = apply { this. =  }
        fun isDepBoss(: ) = apply { this. =  }
        fun isProjectManager(: ) = apply { this. =  }
        fun isActive(: ) = apply { this. =  }
        fun build() = ProgrammerDTO (
                id, name, registerDate, department_id,
                department, activeProjects_ids, activeProjects,
                commits_ids, commits, issues_ids, issues,
                technologies, salary, isDepBoss,
                isProjectManager, isActive
        )
    }
}

 */