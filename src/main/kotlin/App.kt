import kotlin.system.exitProcess

fun main(args: Array<String>) {
    /*
    if (args.size != 1) {
        println("Invalid number of arguments. Only 1 argument required [xml/json]")
        println("Current number of arguments: ${args.size}")
        exitProcess(1707)
    }
     */
    Empresa.checkService()
    Empresa.departments("json")
    Empresa.departments("xml")

    Empresa.projects("json")
    Empresa.projects("xml")

    Empresa.programmers("json")
    Empresa.programmers("xml")

    Empresa.commits("json")
    Empresa.commits("xml")

    Empresa.issues("json")
    Empresa.issues("xml")

    Empresa.repositories("json")
    Empresa.repositories("xml")
    /*
    Empresa.insertCosas(args[0])
    Empresa.findAllCosas(args[0])
    Empresa.getCosasById(args[0])
    Empresa.updateCosas(args[0])
    Empresa.deleteCosas(args[0])

     */
 }