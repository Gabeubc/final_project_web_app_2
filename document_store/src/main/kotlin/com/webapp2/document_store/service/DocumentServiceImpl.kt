package com.webapp2.document_store.service

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.entity.DocumentMetadata
import com.webapp2.document_store.repository.DocumentContentRepository
import com.webapp2.document_store.repository.DocumentMetadataRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentServiceImpl(
    @Autowired
    private val documentRepository: DocumentMetadataRepository,
    @Autowired
    private val documentContentRepository: DocumentContentRepository
) : DocumentService {
    @Transactional
    override fun getDocuments(): List<DocumentDto> {
        return documentRepository.findAll().map { it.toDto() }
    }

    @Transactional
    override fun getDocumentById(metadataId: Long): DocumentDto {
        return documentRepository.findById(metadataId).map { it.toDto() }.orElse(null)
    }

    @Transactional
    override fun getDocumentContentById(metadataId: Long): ByteArray? {
        return documentRepository.findById(metadataId).map { it.toDtoWithContent().content }.orElse(null)
    }

    @Transactional
    override fun saveDocument(documentDto: DocumentDto): Boolean {
        try {
            documentRepository.save(documentDto.toEntity())
            return true
        } catch (e: Exception) {
            return false
        }
    }

    @Transactional
    override fun updateDocument(documentDto: DocumentDto): Boolean {
        try {
            val document = documentRepository.findById(documentDto.id!!).orElse(null)
            updateField(document, documentDto)
            documentRepository.save(document)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    @Transactional
    override fun deleteDocumentById(metadataId: Long): Boolean {
        try {
            documentRepository.deleteById(metadataId)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun updateField(document: DocumentMetadata, documentDto: DocumentDto) {
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
    }
}