package repositories

/**
 * Interfaz que obliga a las clases que la implementen a implementar las operaciones CRUD.
 */
interface IRepository<T, ID> {
    fun findAll() : List<T>
    fun getById(id: ID) : T
    fun insert(t: T) : T
    fun update(t: T) : T
    fun delete(t: T) : T
}