package ru.nachinkina.kotlinOauth.entity

import javax.persistence.*

@Entity
@Table(name = "role", schema = "test")
data class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1,

    @Column(name = "name")
    val name: String? = null
)