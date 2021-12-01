package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "project_programmer")
@XmlAccessorType(XmlAccessType.FIELD)
class Project_Programmer () {
        @XmlAttribute
        lateinit var id: String
        lateinit var project_id: String
        lateinit var programmer_id: String

        constructor(
                id: String,
                project_id: String,
                programmer_id: String
        ) : this() {
                this.id = id
                this.project_id = project_id
                this.programmer_id = programmer_id
        }
}

