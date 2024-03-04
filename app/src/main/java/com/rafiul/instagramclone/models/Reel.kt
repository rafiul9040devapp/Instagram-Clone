package com.rafiul.instagramclone.models

class Reel {

    var reelUrl: String = ""
    var caption: String = ""
    var profileLink : String? = null
    constructor()
    constructor(reelUrl: String, caption: String) {
        this.reelUrl = reelUrl
        this.caption = caption
    }
    constructor(reelUrl: String, caption: String, profileLik: String) {
        this.reelUrl = reelUrl
        this.caption = caption
        this.profileLink = profileLik
    }


}