package com.webapp2.crm.command

interface Command<Output> {
    fun execute(): Output
}