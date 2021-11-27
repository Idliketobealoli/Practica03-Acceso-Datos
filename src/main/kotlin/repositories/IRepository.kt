package repositories

interface IRepository<T> {
    fun findAll() : List<T>
    fun getById(id: String) : T
    fun insert(t: T) : T
    fun update(t: T) : T
    fun delete(t: T) : T
}