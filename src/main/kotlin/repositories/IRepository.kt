package repositories

interface IRepository<T, ID> {
    fun findAll() : List<T>
    fun getById(id: ID) : T
    fun insert(t: T) : T
    fun update(t: T) : T
    fun delete(t: T) : T
}