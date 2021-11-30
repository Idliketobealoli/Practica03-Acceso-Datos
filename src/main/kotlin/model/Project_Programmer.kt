package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "project_programmer")
@XmlAccessorType(XmlAccessType.FIELD)
data class Project_Programmer(
        @XmlAttribute
        var id: String,
        var project_id: String,
        var programmer_id: String
)
