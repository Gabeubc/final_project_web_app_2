package com.webapp2.document_store.service

import com.webapp2.document_store.dto.DocumentDto

interface DocumentService {
    fun getDocuments(): List<DocumentDto>
    fun getDocumentById(metadataId: Long): DocumentDto
    fun getDocumentContentById(metadataId: Long): ByteArray?
    fun saveDocument(documentDto: DocumentDto): Boolean
    fun updateDocument(documentDto: DocumentDto): Boolean
    fun deleteDocumentById(metadataId: Long): Boolean
}