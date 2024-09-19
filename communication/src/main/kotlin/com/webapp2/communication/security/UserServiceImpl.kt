package com.example.api_gateway.service

import com.example.api_gateway.model.User
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val keycloak: Keycloak = KeycloakBuilder.builder()
        .serverUrl("http://localhost:9090/")  // Keycloak server URL
        .realm("crm")  // Replace with the name of your specific realm
        .clientId("admin-cli")  // Use the admin-cli client for admin actions
        .grantType(OAuth2Constants.PASSWORD)
        .username("admin_1")  // Username of the new admin user
        .password("1234")  // Password of the new admin user
        .build()
) : UserService {
    override fun createUser(user: User): String {
        try{

            // Create the user representation
            val credential = CredentialRepresentation().apply {
                isTemporary = false
                type = CredentialRepresentation.PASSWORD
                value = user.password
            }

            val userRepresentation = UserRepresentation().apply {
                username = user.username
                email = "newuser1@example.com"
                isEnabled = true
                credentials = listOf(credential)
            }

            // Get the users resource for the realm
            val usersResource = keycloak.realm("crm").users()

            // Try to create the user
            val response = usersResource.create(userRepresentation)

            // Check the response status
            if (response.status == 201) {
                return "User created successfully."
            } else {
                return "Failed to create user. Status: ${response.status}"
            }
        }catch (e: Exception){
            return e.toString()
        }
    }
}