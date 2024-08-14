package com.webapp2.document_store.exception

class DocumentNotFoundException(
    private val msg: String? = "document not found"
) : Exception(msg)