package com.webapp2.document_store.command

interface Command<Output> {
    fun execute(): Output
}