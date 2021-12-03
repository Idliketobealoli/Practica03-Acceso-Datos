import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Invalid number of arguments. Only 1 argument required [xml/json]")
        println("Current number of arguments: ${args.size}")
        exitProcess(1707)
    }

    Empresa.checkService()
    Empresa.departments(args[0])
    //Empresa.departments("json")

    Empresa.projects(args[0])
    //Empresa.projects("json")

    Empresa.programmers(args[0])
    //Empresa.programmers("json")

    Empresa.commits(args[0])
    //Empresa.commits("json")

    Empresa.issues(args[0])
    //Empresa.issues("json")

    Empresa.repositories(args[0])
    //Empresa.repositories("json")
 }