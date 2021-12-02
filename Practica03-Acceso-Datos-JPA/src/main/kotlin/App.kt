import model.*
import javax.persistence.Persistence


fun main () {
    val commit = Commite(11, "commit", "uwu", "26/05/2002",
            "repo1", "project1", "pManager", "issue1")
    val dep = Department(11, "dep1", "dsvkjbwrogb", -99.9,
            null,null,-9.0,"asdjvbriuvisjvgbivi")
    val issue = Issue(11, "author", "issue", "", "26/05/2002",
            "awa", "awa2", "awa3", 1)
    val programmer = Programmer(11, "prog1", "22/12/2002", "awa",
            "awa2","awa3","awa4","",222220.0,
            0,0,1)
    val project = Project(11, "dep", "awa", "project1",
            -6969.69, "01/01/2001", null, null,
            "aaaaaa",0,null)
    val repo = Repository(11, "repo1", "13/11/9990",
            "cvfnhktyjreg",null,null)


    val entityManagerFactory = Persistence.createEntityManagerFactory("default")
    val entityManager = entityManagerFactory.createEntityManager()
    val transaction = entityManager.transaction

    transaction.begin();
    entityManager.persist(commit);
    println(commit)
    entityManager.persist(dep);
    println(dep)
    entityManager.persist(issue);
    println(issue)
    entityManager.persist(programmer);
    println(programmer)
    entityManager.persist(project);
    println(project)
    entityManager.persist(repo);
    println(repo)
    transaction.commit();

    transaction.begin()
    for (e in entityManager.createQuery("FROM Commite ").resultList) {
        println(e.toString())
    }
    for (e in entityManager.createQuery("FROM Department ").resultList) {
        println(e.toString())
    }
    for (e in entityManager.createQuery("FROM Issue ").resultList) {
        println(e.toString())
    }
    for (e in entityManager.createQuery("FROM Programmer ").resultList) {
        println(e.toString())
    }
    for (e in entityManager.createQuery("FROM Project ").resultList) {
        println(e.toString())
    }
    for (e in entityManager.createQuery("FROM Repository ").resultList) {
        println(e.toString())
    }
    transaction.commit()

    transaction.begin()
    entityManager.merge(commit)
    commit.title = "commit aksjfbwiqogwbgwuoefhweifuj"
    entityManager.merge(dep)
    dep.anualBudget = 420.0
    entityManager.merge(issue)
    issue.text = "awawawawawawawawawawawawawaawawawawawawawawawawawa"
    entityManager.merge(programmer)
    programmer.salary = 420.0
    entityManager.merge(project)
    project.budget = 420.0
    entityManager.merge(repo)
    repo.name = "tengo suenio"
    transaction.commit()

    transaction.begin()
    val com = entityManager.find(Commite::class.java, commit.id)
    entityManager.remove(com)
    println("${com.title} is no more.")
    val depart = entityManager.find(Department::class.java, dep.id)
    entityManager.remove(depart)
    println("${depart.name} is no more.")
    val iss = entityManager.find(Issue::class.java, issue.id)
    entityManager.remove(iss)
    println("${iss.title} is no more.")
    val pro = entityManager.find(Programmer::class.java, programmer.id)
    entityManager.remove(pro)
    println("${pro.name} is no more.")
    val projjjj = entityManager.find(Project::class.java, project.id)
    entityManager.remove(projjjj)
    println("${projjjj.name} is no more.")
    val rep = entityManager.find(Repository::class.java, repo.id)
    entityManager.remove(rep)
    println("${rep.name} is no more.")
    transaction.commit()
}