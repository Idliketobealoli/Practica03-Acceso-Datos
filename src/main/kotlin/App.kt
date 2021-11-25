import java.util.*
import java.util.regex.Pattern

fun main() {
     println("Hello world!")
    ///////////////////////////////////////////////////
    //PRUEBAS DE UUID
    // regex for uuid:
    // [a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}
    val pruebaUUID = UUID.fromString("aaaaaaaa-1234-1234-1234-aaaaaaaaaaaa")
    if (Pattern.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}", pruebaUUID.toString())) {
        println(pruebaUUID)
    }
    else println("awa")
 }