package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "repository")
@XmlAccessorType(XmlAccessType.FIELD)
data class Repository(
        @XmlAttribute
        var id: String,
        var name: String,
        @XmlAttribute
        var creationDate: String,
        var project_id: String,
        var commits_ids: String?,
        var issues_ids: String?
)
