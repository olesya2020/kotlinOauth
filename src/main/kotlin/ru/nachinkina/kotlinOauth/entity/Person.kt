package ru.nachinkina.kotlinOauth.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name ="person", schema="test")
data class Person(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1,

        @Column(nullable = false)
        val name: String? = null

)

