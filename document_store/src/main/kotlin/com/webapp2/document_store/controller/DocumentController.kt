package com.webapp2.document_store.controller

import com.webapp2.document_store.command.*
import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.utils.GeneralConstant
import org.springframework.beans.factory.BeanFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(path = ["/API/documents"])
class DocumentController(
    private val beanFactory: BeanFactory
) {
    @GetMapping(value = ["/", ""])
    fun getDocuments(): ResponseEntity<List<DocumentDto>> {
        val command = beanFactory.getBean(GetDocumentCommand::class.java)
        command.setMetadataId(GeneralConstant.EMPTY_ID)
        return ResponseEntity.ok(command.execute())
    }

    @GetMapping(value = ["/{metadataId}"])
    fun getDocumentById(
        @PathVariable(name = "metadataId") metadataId: Long
    ): ResponseEntity<List<DocumentDto>> {
        val command = beanFactory.getBean(GetDocumentCommand::class.java)
        command.setMetadataId(metadataId)
        return ResponseEntity.ok(command.execute())
    }

    @GetMapping(value = ["/{metadataId}/data"])
    fun getDocumentDataById(
        @PathVariable(name = "metadataId") metadataId: Long
    ): ResponseEntity<ByteArray> {
        val command = beanFactory.getBean(GetDocumentContentCommand::class.java)
        command.setMetadataId(metadataId)
        return ResponseEntity.ok(command.execute())
    }

    @PostMapping(value = ["/", ""])
    fun saveDocument(
        @RequestParam(name = "file") file: MultipartFile,
        @RequestParam(name = "name") name: String
    ): ResponseEntity<Boolean> {
        val command = beanFactory.getBean(SaveDocumentCommand::class.java)
        command.setParameters(GeneralConstant.EMPTY_ID, file, name, GeneralConstant.SAVE)
        return ResponseEntity.ok(command.execute())
    }

    @PutMapping(value = ["/{metadataId}"])
    fun updateDocument(
        @RequestParam(name = "file") file: MultipartFile,
        @RequestParam(name = "name") name: String,
        @PathVariable(name = "metadataId") metadataId: Long
    ): ResponseEntity<Boolean> {
        val command = beanFactory.getBean(SaveDocumentCommand::class.java)
        command.setParameters(metadataId, file, name, GeneralConstant.UPDATE)
        return ResponseEntity.ok(command.execute())
    }

    @DeleteMapping(value = ["/{metadataId}"])
    fun deleteDocumentMetadataById(
        @PathVariable(name = "metadataId") metadataId: Long
    ): ResponseEntity<Boolean> {
        val command = beanFactory.getBean(DeleteCommand::class.java)
        command.setMetadataId(metadataId)
        return ResponseEntity.ok(command.execute())
    }
}