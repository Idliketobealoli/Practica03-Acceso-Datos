package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

/**
 * Clase POKO Programmer_Issue cuyos campos irán a la base de datos.
 * @author Daniel Rodríguez
 */
@XmlRootElement(name = "programmer_issue")
@XmlAccessorType(XmlAccessType.FIELD)
class Programmer_Issue () {
        @XmlAttribute
        lateinit var id: String
        lateinit var programmer_id: String
        lateinit var issue_id: String

        constructor(
                id: String,
                programmer_id: String,
                issue_id: String
        ) : this() {
                this.id = id
                this.programmer_id = programmer_id
                this.issue_id = issue_id
        }
}
