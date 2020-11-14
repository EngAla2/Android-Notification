package com.example.tryandroidnotif

class  ListViewModel{
    var id: Int? = null
    var title: String? = null
    var content: Boolean? = null

    constructor(id: Int, title: String, content: Boolean) {
        this.id = id
        this.title = title
        this.content = content
    }
}