package repositories

import db.DBController
import model.Repository
import java.sql.ResultSet
import java.sql.SQLException

class RepositoryRepository : IRepository<Repository, String> {
    override fun findAll(): List<Repository> {
        val query = "select * from Repository"
        val repositories = ArrayList<Repository>()
        DBController.open()
        val result = DBController.select(query) ?: throw SQLException(
                "Error at selecting all repositories."
        )
        while (result.next()) {
            val repository = getRepositoryFromResultSet(result)
            repositories.add(repository)
        }
        DBController.close()
        return repositories
    }

    override fun getById(id: String): Repository {
        val query = "select * from Repository where id = ?"
        DBController.open()
        val result = DBController.select(query, id) ?:
        throw SQLException("Error while consulting Repository with id: $id")
        var repositoryResult : Repository? = null
        if (result.next()) {
            repositoryResult = getRepositoryFromResultSet(result)
        }
        DBController.close()
        return repositoryResult ?: throw SQLException("Error: Repository with id $id does not exist.")
    }

    private fun getRepositoryFromResultSet(result: ResultSet): Repository {
        return Repository(
                result.getString("id"),
                result.getString("name"),
                result.getString("creationDate"),
                result.getString("project_id"),
                result.getString("commits_ids"),
                result.getString("issues_ids")
        )
    }

    override fun insert(repository: Repository): Repository {
        val query = "insert into Repository values (?, ?, ?, ?, ?, ?)"
        DBController.open()
        val result = DBController.insert(
                query, repository.id, repository.name,
                repository.creationDate, repository.project_id,
                repository.commits_ids, repository.issues_ids
        ) ?: throw SQLException("Error: could not insert Repository.")
        if (result.next()) {
            DBController.close()
            return repository
        }
        else throw SQLException("Error: could not insert Repository into DB")
    }

    override fun update(repository: Repository): Repository {
        val query = ("update Repository set name = ?, creationDate = ?, project_id = ?, " +
                "commits_ids = ?, issues_ids = ? where id = ?")
        DBController.open()
        val result = DBController.update(
                query, repository.name, repository.creationDate, repository.project_id,
                repository.commits_ids, repository.issues_ids, repository.id
        )
        DBController.close()
        if (result > 0) {
            return repository
        }
        else throw SQLException("Error: could not update Repository with id ${repository.id}")
    }

    override fun delete(repository: Repository): Repository {
        val query = "delete from Repository where id = ?"
        DBController.open()
        val result = DBController.delete(query, repository.id)
        DBController.close()
        if (result > 0) {
            return repository
        }
        else throw SQLException("Error: could not delete Repository with id ${repository.id}")
    }
}