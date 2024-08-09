package com.webapp2.document_store

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DocumentStoreApplication

fun main(args: Array<String>) {
	runApplication<DocumentStoreApplication>(*args)
}
