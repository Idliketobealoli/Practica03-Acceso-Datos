package services

import dto.DepartmentDTO
import mappers.DepartmentMapper
import model.Department
import repositories.DepartmentRepository

class DepartmentService : BaseService<Department, String, DepartmentRepository>(DepartmentRepository()) {
    private val mapper = DepartmentMapper()

    fun getAllDepartments() : List<DepartmentDTO> {
        return mapper.toDTO(this.findAll())
    }

    fun getDepartmentById(id: String) : DepartmentDTO {
        return mapper.toDTO(this.getById(id))
    }

    fun createDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.insert(mapper.fromDTO(dep)))
    }

    fun updateDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.update(mapper.fromDTO(dep)))
    }

    fun deleteDepartment(dep : DepartmentDTO) : DepartmentDTO {
        return mapper.toDTO(this.delete(mapper.fromDTO(dep)))
    }
}