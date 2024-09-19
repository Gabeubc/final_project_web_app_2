package com.webapp2.api_gateway.service

import com.webapp2.api_gateway.model.User
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    /*
    private val keycloak: Keycloak = KeycloakBuilder.builder()
        .serverUrl("http://localhost:8080")  // Replace with your Keycloak server URL
        .realm("master")  // The realm you want to manage users in
        .clientId("admin-cli")  // Default client for administrative tasks
        .grantType(OAuth2Constants.PASSWORD)
        .username("admin")  // Admin username
        .password("password")  // Admin password
        .build()
    */
) : UserServcice {
    /*
    override fun createUser(user: User): Boolean {
        try{

            val realmResource = keycloak.realm("your-realm")  // Replace with your Keycloak realm
            val usersResource = realmResource.users()

            // Create a new user representation
            val userRepresentation = UserRepresentation().apply {
                this.username = username
                isEnabled = true
            }

            // Create the user in Keycloak
            usersResource.create(userRepresentation)

            // Find the created user by username to get their ID
            val userId = usersResource.search(user.username).first().id

            // Set up password credentials for the new user
            val passwordCredentials = CredentialRepresentation().apply {
                isTemporary = false  // Make the password permanent
                type = CredentialRepresentation.PASSWORD
                value = user.password
            }

            // Set the password for the created user
            usersResource.get(userId).resetPassword(passwordCredentials)
            return true
        }catch (e: Exception){
            return false
        }
    }
     */
    override fun createUser(user: User): Boolean {
        TODO("Not yet implemented")
    }
}