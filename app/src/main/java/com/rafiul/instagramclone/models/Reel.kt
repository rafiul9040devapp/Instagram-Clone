package com.rafiul.instagramclone.models

class Reel {

    var reelUrl: String = ""
    var caption: String = ""
    constructor()
    constructor(reelUrl: String, caption: String) {
        this.reelUrl = reelUrl
        this.caption = caption
    }
}