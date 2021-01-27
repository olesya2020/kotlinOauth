package ru.nachinkina.kotlinOauth.oauth

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class NewUser : Serializable {

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("password")
    var password: String? = null

    constructor() {}

    constructor(username: String, password: String, recaptchaToken: String) {
        this.username = username
        this.password = password
    }

    companion object {
        private const val serialVersionUID = -1764970284520387975L
    }
}