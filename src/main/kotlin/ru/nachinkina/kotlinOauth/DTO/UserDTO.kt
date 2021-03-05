package ru.nachinkina.kotlinOauth.DTO

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
class UserDTO {

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("password")
    var password: String? = null

}