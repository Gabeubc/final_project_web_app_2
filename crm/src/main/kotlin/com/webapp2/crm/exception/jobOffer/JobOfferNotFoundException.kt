package com.webapp2.crm.exception.jobOffer

class JobOfferNotFoundException(
    private val msg: String = "job offer not found"
): Exception(msg)