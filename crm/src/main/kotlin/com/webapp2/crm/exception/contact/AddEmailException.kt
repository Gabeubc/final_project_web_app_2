package com.webapp2.crm.exception.contact

class AddEmailException(
    private val msg: String = "adding email fail"
): Exception(msg) {
}