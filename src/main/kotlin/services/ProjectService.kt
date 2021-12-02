package services

import dto.ProjectDTO
import mappers.ProjectMapper
import model.Project
import repositories.ProjectRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Daniel Rodriguez
 * @see BaseService
 * @see ProjectMapper
 * @see ProjectRepository
 */
class ProjectService : BaseService<Project, String, ProjectRepository>(ProjectRepository()) {
    val mapper = ProjectMapper()

    /**
     * Llama a ProjectRepository para que busque todos los ProjectDTO en la base de datos,
     * los mapea y los devuelve como lista de ProjectDTO.
     * @author Daniel Rodriguez
     * @see ProjectMapper
     * @see ProjectRepository
     */
    fun getAllProjects() : List<ProjectDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a ProjectRepository para que busque el ProjectDTO en la base de datos
     * cuyo id corresponda con el introducido por parámetro,
     * lo mapea y lo devuelve como ProjectDTO.
     * @author Daniel Rodriguez
     * @see ProjectMapper
     * @see ProjectRepository
     */
    fun getProjectById(id: String) : ProjectDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a ProjectRepository para que inserte el ProjectDTO en la base de datos,
     * lo mapea y lo devuelve como ProjectDTO.
     * @author Daniel Rodriguez
     * @see ProjectMapper
     * @see ProjectRepository
     */
    fun createProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(proj)))
    }

    /**
     * Llama a ProjectRepository para que actualice el ProjectDTO en la base de datos,
     * lo mapea y lo devuelve como ProjectDTO.
     * @author Daniel Rodriguez
     * @see ProjectMapper
     * @see ProjectRepository
     */
    fun updateProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(proj)))
    }

    /**
     * Llama a ProjectRepository para que borre el ProjectDTO en la base de datos,
     * lo mapea y lo devuelve como ProjectDTO.
     * @author Daniel Rodriguez
     * @see ProjectMapper
     * @see ProjectRepository
     */
    fun deleteProject(proj : ProjectDTO) : ProjectDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(proj)))
    }
}