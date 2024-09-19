package com.webapp2.crm.exception.contact

class ContactNotFoundException(
   val msg: String = "requested contact not found"
): Exception(msg)