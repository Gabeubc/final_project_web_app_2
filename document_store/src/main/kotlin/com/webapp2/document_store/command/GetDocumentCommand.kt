package com.webapp2.document_store.command

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.service.DocumentService
import com.webapp2.document_store.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component("GetDocumentCommand")
class GetDocumentCommand(
    @Autowired
    private val documentService: DocumentService,
    private val metadataId: Long
) : Command<List<DocumentDto>>{
    override fun execute(): List<DocumentDto> {
        if(GeneralConstant.EMPTY_ID.equals(metadataId)){
            return documentService.getDocuments();
        }
        return listOf(documentService.getDocumentById(metadataId))
    }
}