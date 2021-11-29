package services

import dto.ProjectDTO
import mappers.ProjectMapper
import model.Project
import repositories.ProjectRepository

class ProjectService : BaseService<Project, String, ProjectRepository>(ProjectRepository()) {
    val mapper = ProjectMapper()

    fun getAllProjects() : List<ProjectDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getProjectById(id: String) : ProjectDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun createProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(proj)))
    }

    fun updateProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(proj)))
    }

    fun deleteProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(proj)))
    }
}