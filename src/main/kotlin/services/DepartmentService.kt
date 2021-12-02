package services

import dto.DepartmentDTO
import mappers.DepartmentMapper
import model.Department
import repositories.DepartmentRepository

/**
 * Clase encargada de llamar al mapper y decirle que mapee el resultado obtenido de la consulta correspondiente.
 * @author Jaime Salcedo
 * @see BaseService
 * @see DepartmentRepository
 * @see DepartmentMapper
 */
class DepartmentService : BaseService<Department, String, DepartmentRepository>(DepartmentRepository()) {
    private val mapper = DepartmentMapper()

    /**
     * Llama a DepartmentRepository para que busque todos los DepartmentDTO en la base de datos,
     * los mapea y los devuelve como lista de DepartmentDTO.
     * @author Jaime Salcedo
     * @see DepartmentMapper
     * @see DepartmentRepository
     */
    fun getAllDepartments() : List<DepartmentDTO> {
        return mapper.toDTO(this.findAll())
    }

    /**
     * Llama a DepartmentRepository para que busque el DepartmentDTO cuyo id corresponda con el string
     * pasado como argumento en la base de datos, lo mapea y lo devuelve como DepartmentDTO.
     * @author Jaime Salcedo
     * @see DepartmentMapper
     * @see DepartmentRepository
     */
    fun getDepartmentById(id: String) : DepartmentDTO {
        return mapper.toDTO(this.getById(id))
    }

    /**
     * Llama a DepartmentRepository para que inserte el DepartmentDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como DepartmentDTO.
     * @author Jaime Salcedo
     * @see DepartmentMapper
     * @see DepartmentRepository
     */
    fun createDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(dep)))
    }

    /**
     * Llama a DepartmentRepository para que modifique el DepartmentDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como DepartmentDTO.
     * @author Jaime Salcedo
     * @see DepartmentMapper
     * @see DepartmentRepository
     */
    fun updateDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(dep)))
    }

    /**
     * Llama a DepartmentRepository para que borre el DepartmentDTO pasado como argumento en la base de datos,
     * lo mapea y lo devuelve como DepartmentDTO.
     * @author Jaime Salcedo
     * @see DepartmentMapper
     * @see DepartmentRepository
     */
    fun deleteDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(dep)))
    }
}