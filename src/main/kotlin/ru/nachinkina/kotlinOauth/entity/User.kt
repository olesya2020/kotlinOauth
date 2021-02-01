package ru.nachinkina.kotlinOauth.entity

import javax.persistence.*

@Entity
@Table(name = "user", schema="test")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test.user_id_seq")
    val id: Long = -1,

    @Column(name="username")
    var username: String? = null,

    @Column(name="password")
    var password: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="roleid")
    var role: Role? = null

)