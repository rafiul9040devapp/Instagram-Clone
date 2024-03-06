package com.rafiul.instagramclone.models

class Post {
    var postUrl: String = ""
    var caption: String = ""
    var name: String = ""
    var image: String = ""
    var time: String = ""

    constructor()
    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }

    constructor(postUrl: String, caption: String, name: String, time: String) {
        this.postUrl = postUrl
        this.caption = caption
        this.name = name
        this.time = time
    }

    constructor(postUrl: String, caption: String, name: String, image: String, time: String) {
        this.postUrl = postUrl
        this.caption = caption
        this.name = name
        this.image = image
        this.time = time
    }

}