package com.webapp2.crm.exception.contact

class DeleteEmailException(
    private val msg: String = "Deleting email fail"
): Exception(msg) {
}