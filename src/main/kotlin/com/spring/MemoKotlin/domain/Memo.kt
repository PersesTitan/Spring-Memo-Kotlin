package com.spring.MemoKotlin.domain

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Memo(title: String, content: String) {

    @Id @GeneratedValue
    @Column(name = "memo_id", nullable = false)
    val id: Long? = null

    var title: String
    var content: String

    val createDate: LocalDateTime

    init {
        this.title = title
        this.content = content
        this.createDate = LocalDateTime.now()
    }
}