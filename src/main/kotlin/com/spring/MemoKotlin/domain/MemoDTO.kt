package com.spring.MemoKotlin.domain

class MemoDTO(title: String, content: String) {

    var title: String
    var content: String

    init {
        this.title = title
        this.content = content
    }
}