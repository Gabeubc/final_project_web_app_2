package com.example.api_gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("config.keycloak")
data class KeycloakConfig (
    val serverUrl: String,
    val realm: String,
    val adminCli: String,
    val adminUser: String,
    val password: String
)