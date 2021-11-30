package mappers

import dto.IssueDTO
import model.Issue
import repositories.ProgrammerRepository
import repositories.ProjectRepository
import repositories.RepositoryRepository
import utils.Utils

class IssueMapper : BaseMapper<Issue, IssueDTO>() {
    override fun fromDTO(item: IssueDTO): Issue {
        Utils().makeSureTheseAreIds(item.id, item.author.id,
                item.project.id, item.repository.id)
        Utils().makeSureThisGuyIsProjectManager(item.author, item.project.id)
        return Issue(
                item.id, item.author.id, item.title,
                item.text, Utils().matchesDate(item.date),
                Utils().getProgrammersIDS(item.programmers),
                item.project.id, item.repository.id,
                Utils().booleanToInt(item.isFinished)
        )
    }

    override fun toDTO(item: Issue): IssueDTO {
        Utils().makeSureTheseAreIds(item.id, item.author_id,
                item.project_id, item.repository_id)
        val author = ProgrammerRepository().getById(item.author_id)
        Utils().makeSureThisGuyIsProjectManager(author, item.project_id)
        return IssueDTO(
                item.id, author, item.title, item.text,
                Utils().matchesDate(item.date),
                Utils().getProgrammers(item.programmers_ids),
                ProjectRepository().getById(item.project_id),
                RepositoryRepository().getById(item.repository_id),
                Utils().intToBoolean(item.isFinished)
        )
    }
}