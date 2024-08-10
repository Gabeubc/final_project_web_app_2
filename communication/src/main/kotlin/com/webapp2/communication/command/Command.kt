package com.webapp2.communication.command

interface Command<Output> {
    fun execute(): Output
}