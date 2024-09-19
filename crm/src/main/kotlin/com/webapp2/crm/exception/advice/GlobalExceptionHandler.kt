package com.webapp2.crm.exception.advice

import com.webapp2.crm.exception.contact.AssignToException
import com.webapp2.crm.exception.contact.ContactCreationException
import com.webapp2.crm.exception.contact.ContactNotFoundException
import com.webapp2.crm.exception.contact.DeleteEmailException
import com.webapp2.crm.exception.jobOffer.ChangeJobOfferStateException
import com.webapp2.crm.exception.jobOffer.JobOfferCreationException
import com.webapp2.crm.exception.jobOffer.JobOfferNotFoundException
import com.webapp2.crm.exception.message.ChangeMessageStateException
import com.webapp2.crm.exception.message.CreateMessageException
import com.webapp2.crm.exception.message.MessageNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ContactNotFoundException::class)
    fun handleContactNotFoundException(ex: ContactNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ContactCreationException::class)
    fun handleContactCreationException(ex: ContactCreationException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DeleteEmailException::class)
    fun handleDeleteEmailException(ex: DeleteEmailException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(AssignToException::class)
    fun handleAssignToException(ex: AssignToException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(JobOfferNotFoundException::class)
    fun handleJobOfferNotFoundException(ex: JobOfferNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(JobOfferCreationException::class)
    fun handleJobOfferCreationException(ex: JobOfferCreationException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ChangeJobOfferStateException::class)
    fun handleChangeJobOfferStateException(ex: ChangeJobOfferStateException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MessageNotFoundException::class)
    fun handleMessageNotFoundException(ex: MessageNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(CreateMessageException::class)
    fun handleCreateMessageException(ex: CreateMessageException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ChangeMessageStateException::class)
    fun handleChangeMessageStateException(ex: ChangeMessageStateException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("An unexpected error occurred: " + ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
