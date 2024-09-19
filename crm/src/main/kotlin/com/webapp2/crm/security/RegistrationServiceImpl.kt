package com.webapp2.crm.security

import com.webapp2.crm.security.config.KeycloakConfig
import com.webapp2.crm.dto.contact.ContactDto
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

@Service
class RegistrationServiceImpl(
    private val keycloakConfig: KeycloakConfig
) : RegistrationService {
    private val keycloak: Keycloak = KeycloakBuilder.builder()
        .serverUrl(keycloakConfig.serverUrl)  // Keycloak server URL
        .realm(keycloakConfig.realm)  // Replace with the name of your specific realm
        .clientId(keycloakConfig.adminCli)  // Use the admin-cli client for admin actions
        .grantType(OAuth2Constants.PASSWORD)
        .username(keycloakConfig.adminUser)  // Username of the new admin user
        .password(keycloakConfig.password)  // Password of the new admin user
        .build()

    override fun createUser(contactDto: ContactDto): String {
        try {
            // Get the realm
            val realmResource = keycloak.realm(keycloakConfig.realm)

            val response = createUser(realmResource.users(), contactDto)

            // Check the response status
            if(response != null){
                if ( response.status == 201) {
                    assignRoleToCreatedUser(realmResource, contactDto)
                    // set contactId at the end of all operation
                    contactDto.id = realmResource.users().search(contactDto.username).first().id.toLong()
                    return contactDto.id.toString()
                } else {
                    return "Failed to create user. Status: ${response.status}"
                }
            }else{
                return "Failed to create user. 500"
            }
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private fun createUser(userResource: UsersResource, contactDto: ContactDto): Response? {

        // Create the user representation
        val credential = CredentialRepresentation().apply {
            isTemporary = false
            type = CredentialRepresentation.PASSWORD
            value = contactDto.password
        }

        val userRepresentation = UserRepresentation().apply {
            username = contactDto.username
            email = contactDto.informations.first().email
            isEnabled = true
            credentials = listOf(credential)
        }

        // Get the users resource for the realm
        val usersResource = keycloak.realm(keycloakConfig.realm).users()

        // Try to create the user
        val response = usersResource.create(userRepresentation)


        return response
    }

    private fun assignRoleToCreatedUser(
        realmResource: RealmResource,
        contactDto: ContactDto
    ) {

        val userId = realmResource.users().search(contactDto.username).first().id

        // Retrieve the role you want to assign (e.g., "admin")
        val realmRole = realmResource.roles().get(contactDto.type).toRepresentation()

        // Get the user resource
        val userResource = realmResource.users().get(userId)

        // Assign the realm role to the user
        userResource.roles().realmLevel().add(listOf(realmRole))
    }
}