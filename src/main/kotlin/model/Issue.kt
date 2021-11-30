package model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
data class Issue(
        @XmlAttribute
        var id: String,
        var author_id: String,
        var title: String,
        var text: String?,
        @XmlAttribute
        var date: String,
        var programmers_ids: String?,
        var project_id: String,
        var repository_id: String,
        var isFinished: Int
)
