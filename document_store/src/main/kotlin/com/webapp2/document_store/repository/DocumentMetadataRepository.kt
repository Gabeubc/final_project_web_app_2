package com.webapp2.document_store.repository

import com.webapp2.document_store.entity.DocumentMetadata
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentMetadataRepository : JpaRepository<DocumentMetadata, Long>{
}