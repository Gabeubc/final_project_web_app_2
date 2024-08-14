package com.webapp2.document_store.exception.advice

import com.webapp2.document_store.exception.DeleteDocumentException
import com.webapp2.document_store.exception.DocumentNotFoundException
import com.webapp2.document_store.exception.UpdateDocumentException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class DocumentControllerAdvice {
    @ExceptionHandler(UpdateDocumentException::class)
    fun handleUpdateDocumentException(
        ex: UpdateDocumentException
    ): ResponseEntity<String> {
        return ResponseEntity(ex.message , HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(DocumentNotFoundException::class)
    fun handleDocumentNotFoundException(
        ex: DocumentNotFoundException
    ): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DeleteDocumentException::class)
    fun handleDeleteDocumentException(
        ex: DeleteDocumentException
    ): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    // General exception handler for any other exceptions
    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        ex: Exception
    ): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}