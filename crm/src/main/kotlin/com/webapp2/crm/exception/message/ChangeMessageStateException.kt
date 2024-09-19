package com.webapp2.crm.exception.message

class ChangeMessageStateException(
    private val msg: String = "updating message state fail"
): Exception(msg) {
}