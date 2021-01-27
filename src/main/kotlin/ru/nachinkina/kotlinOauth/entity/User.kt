package ru.nachinkina.kotlinOauth.entity

import javax.persistence.*

@Entity
@Table(name = "user", schema="test")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1,

    @Column(name="username")
    var username: String = "",

    @Column(name="password")
    var password: String = "",

    @ManyToOne
    var role: Role? = null

)