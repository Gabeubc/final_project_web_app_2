package com.webapp2.api_gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


@Configuration
@EnableMethodSecurity
class SecurityConfig( val crr: ClientRegistrationRepository ) {


    fun oidcLogoutSuccessHandler() = OidcClientInitiatedLogoutSuccessHandler( crr )
        .also{
            it.setPostLogoutRedirectUri("http://localhost:8080/")
        }

    @Bean
    fun securityFilterChain( httpSecurity: HttpSecurity ) : SecurityFilterChain{
        return httpSecurity
            .authorizeHttpRequests{
                it.requestMatchers(
                    "/",
                    "/save"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login {  }
            .oauth2Client {  }
            .csrf{
                it.csrfTokenRepository(
                    CookieCsrfTokenRepository.withHttpOnlyFalse()
                )
            }
            .cors { it.disable() }
            .logout{
                it.logoutSuccessHandler(
                    oidcLogoutSuccessHandler()
                )
            }
            .build()
    }


}