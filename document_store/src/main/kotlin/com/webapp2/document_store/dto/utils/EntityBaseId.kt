package com.webapp2.document_store.dto.utils

import com.example.crm.entities.utils.EntityBaseId
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.util.ProxyUtils
import java.io.Serializable

@MappedSuperclass
abstract class EntityBaseId < T: Serializable> {

    companion object{
        val versionUID = "073a62c1-8a6d-4a1f-b7d4-9f696b42baee"
    }

    @Id
    @GeneratedValue
    var id : T? = null;

    override fun toString(): String {
        return "@Entity ${this.javaClass.name}(id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true
        if (javaClass != ProxyUtils.getUserClass(other))
            return false
        other as EntityBaseId<*>
        return if (null == id) false
        else this.id == other.id
    }
    override fun hashCode(): Int {
        return 31
    }
}