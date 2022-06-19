package com.spring.MemoKotlin.domain

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Memo(title: String, content: String) {

    @Id @GeneratedValue
    @Column(name = "memo_id", nullable = false)
    private val id: Long? = null

    @Getter
    private var title: String
    @Getter
    private var content: String

    private val createDate: LocalDateTime

    init {
        this.title = title
        this.content = content
        this.createDate = LocalDateTime.now()
    }

    fun createMemo(title: String, content: String): Memo {
        return Memo(title, content)
    }
}