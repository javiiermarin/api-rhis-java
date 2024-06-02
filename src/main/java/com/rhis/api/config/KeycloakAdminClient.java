package com.rhis.api.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminClient {

    @Value("${keycloak-realm}")
    private String keycloakRealm;

    @Value("${keycloak-server}")
    private String keycloakServer;

    @Value("${keycloak-client-id}")
    private String keycloakClient;

    @Value("${keycloak-client-secret}")
    private String keycloakClientSecret;

    @Bean
    protected Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServer)
                .realm(keycloakRealm)
                .clientId(keycloakClient)
                .clientSecret(keycloakClientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    @Bean
    public RealmResource keycloakAdminRealmResource() {
        return keycloak().realm(keycloakRealm);
    }
}
