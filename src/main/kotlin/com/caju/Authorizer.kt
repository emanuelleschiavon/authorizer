package com.caju

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Authorizer

fun main(args: Array<String>) {
	runApplication<Authorizer>(*args)
}
