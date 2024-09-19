package com.webapp2.crm.exception.jobOffer

class JobOfferCreationException(
    private val msg: String = "job offer creation fail"
): Exception(msg) {
}