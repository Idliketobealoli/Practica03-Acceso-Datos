package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "commit")
@XmlAccessorType(XmlAccessType.FIELD)
data class Commit(
        @XmlAttribute
        var id: String,
        var title: String,
        var text: String?,
        @XmlAttribute
        var date: String,
        var repository_id: String,
        var project_id: String,
        @XmlAttribute
        var author_id: String,
        var issue_id: String
)
