package com.webapp2.document_store.exception

class UpdateDocumentException (
    private val msg: String = "error occur when updating the document"
) : Exception(msg)