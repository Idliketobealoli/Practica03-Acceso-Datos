fun main() {
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
 }