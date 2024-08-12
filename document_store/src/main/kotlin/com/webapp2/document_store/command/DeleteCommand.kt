package com.webapp2.document_store.command

import com.webapp2.document_store.service.DocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("DeleteCommand")
class DeleteCommand(
    @Autowired
    private val documentService: DocumentService,
) : Command<Boolean>{

    private var metadataId: Long = 0

    fun setMetadataId(metadataId: Long) {
        this.metadataId = metadataId
    }
    override fun execute(): Boolean {
        return documentService.deleteDocumentById(metadataId)
    }
}