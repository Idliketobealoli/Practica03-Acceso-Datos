package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "programmer_issue")
@XmlAccessorType(XmlAccessType.FIELD)
data class Programmer_Issue(
        @XmlAttribute
        var id: String,
        var programmer_id: String,
        var issue_id: String
)
