package com.webapp2.crm.exception.jobOffer

class ChangeJobOfferStateException(
    private val msg: String = "updating job offer state fail"
): Exception(msg) {
}