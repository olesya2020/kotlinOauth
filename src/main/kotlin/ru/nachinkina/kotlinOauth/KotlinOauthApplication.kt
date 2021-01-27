package ru.nachinkina.kotlinOauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class KotlinOauthApplication

fun main(args: Array<String>) {
	runApplication<KotlinOauthApplication>(*args)
}
