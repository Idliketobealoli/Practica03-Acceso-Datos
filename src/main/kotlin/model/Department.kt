package model

data class Department(
        var id: String,
        var name: String,
        var boss_id: String,
        var budget: Double,
        var finishedProjects_ids: List<String>,
        var developingProjects_ids: List<String>,
        var anualBudget: Double,
        var bossHistory_ids: List<String>
)
