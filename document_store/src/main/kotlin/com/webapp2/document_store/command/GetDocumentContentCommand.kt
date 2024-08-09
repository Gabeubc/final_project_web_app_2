package com.webapp2.document_store.command

import com.webapp2.document_store.service.DocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("GetDocumentContentCommand")
class GetDocumentContentCommand(
    @Autowired
    private val documentService: DocumentService,
    private val metadataId: Long
) : Command<ByteArray> {
    override fun execute(): ByteArray {
        return documentService.getDocumentContentById(metadataId)!!
    }
}