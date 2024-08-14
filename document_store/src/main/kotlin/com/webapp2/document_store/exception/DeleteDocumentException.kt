package com.webapp2.document_store.exception

class DeleteDocumentException (
    private val msg: String = "error occur when deleting the document"
) : Exception(msg)