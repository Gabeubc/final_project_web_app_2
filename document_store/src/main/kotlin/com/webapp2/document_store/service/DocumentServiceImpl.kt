package com.webapp2.document_store.service

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.entity.DocumentMetadata
import com.webapp2.document_store.repository.DocumentMetadataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl(
    @Autowired
    private val documentRepository: DocumentMetadataRepository
) : DocumentService {
    override fun getDocuments(): List<DocumentDto> {
        return documentRepository.findAll().map { it.toDto() }
    }

    override fun getDocumentById(metadataId: Long): DocumentDto {
        return documentRepository.findById(metadataId).map { it.toDto() }.orElse(null)
    }

    override fun getDocumentContentById(metadataId: Long): ByteArray? {
        return documentRepository.findById(metadataId).map { it.toDtoWithContent().content }.orElse(null)
    }

    override fun saveDocument(documentDto: DocumentDto): Boolean {
        try {
            documentRepository.save(documentDto.toEntity())
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun updateDocument(documentDto: DocumentDto): Boolean {
        try {
            val document = documentRepository.findById(documentDto.id!!).orElse(null)
            if (documentDto.name != null)
                document.name = documentDto.name!!
            if (documentDto.contentType != null)
                document.contentType = documentDto.contentType!!
            if (documentDto.size != null)
                document.size = documentDto.size!!
            if (documentDto.timeStamp != null)
                document.timeStamp = documentDto.timeStamp!!
            if (documentDto.content != null)
                document.document.content = documentDto.content
            documentRepository.save(documentDto.toEntity())
            return true
        } catch (e: Exception) {
            return false
        }
    }


    override fun deleteDocumentById(metadataId: Long): Boolean {
        try {
            documentRepository.deleteById(metadataId)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}