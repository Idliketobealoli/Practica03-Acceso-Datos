package services

import repositories.IRepository

abstract class BaseService <T, ID, R : IRepository<T, ID>>(rep : R) {
    protected val repository = rep

    fun findAll() : List<T> {
        return repository.findAll()
    }

    fun getById(id: ID) : T {
        return repository.getById(id)
    }

    fun insert(t: T) : T {
        return repository.insert(t)
    }

    fun update(t: T) : T {
        return repository.update(t)
    }

    fun delete(t: T) : T {
        return repository.delete(t)
    }
}