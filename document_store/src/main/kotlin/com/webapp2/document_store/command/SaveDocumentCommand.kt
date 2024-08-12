package com.webapp2.document_store.command

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.service.DocumentService
import com.webapp2.document_store.utils.GeneralConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Component("SaveDocumentCommand")
class SaveDocumentCommand @Autowired constructor(
    private val documentService: DocumentService
) : Command<Boolean> {


    private var metadataId: Long = 0
    private lateinit var file: MultipartFile
    private lateinit var name: String
    private lateinit var operation: String

    fun setParameters(
        metadataId: Long,
        file: MultipartFile,
        name: String,
        operation: String
    ) {
        this.metadataId = metadataId
        this.file = file
        this.name = name
        this.operation = operation
    }

    override fun execute(): Boolean {
        var documentDto = DocumentDto()
        when (operation) {
            GeneralConstant.SAVE -> {
                return documentService.saveDocument(
                    DocumentDto(
                        file.bytes,
                        name,
                        file.bytes.size,
                        LocalDateTime.now(),
                        file.contentType
                    )
                )
            }

            GeneralConstant.UPDATE -> {
                if (!file.isEmpty) {
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
                return documentService.updateDocument(documentDto)
            }

            else -> return false
        }
    }
}
