package com.webapp2.document_store.command

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.service.DocumentService
import com.webapp2.document_store.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Component("SaveDocumentCommand")
class SaveDocumentCommand(
    @Autowired
    private val documentService: DocumentService,
    private val metadataId: Long,
    private val file: MultipartFile,
    private val name: String,
    private val operation: String,
) : Command<Boolean> {
    override fun execute(): Boolean {
        var documentDto = DocumentDto()
        if (GeneralConstant.SAVE == operation) {
            return documentService.saveDocument(
                DocumentDto(
                    file.bytes,
                    name,
                    file.bytes.size,
                    LocalDateTime.now(),
                    file.contentType
                )
            )
        } else if (GeneralConstant.UPDATE == operation) {
            if (file != null && !file.isEmpty) {
                documentDto = DocumentDto(
                    file.bytes,
                    name,
                    file.bytes.size,
                    LocalDateTime.now(),
                    file.contentType
                )
            } else {
                documentDto.name = name
            }
            documentDto.id = metadataId
            return documentService.saveDocument(documentDto)
        }
        return false
    }
}