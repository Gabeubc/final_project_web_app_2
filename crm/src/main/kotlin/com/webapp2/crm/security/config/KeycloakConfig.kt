package com.webapp2.crm.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix= "config.keycloak")
class KeycloakConfig {
    var serverUrl: String = ""
    var realm: String = ""
    var adminCli: String = ""
    var adminUser: String = ""
    var password: String = ""
}