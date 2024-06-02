package com.rhis.api.service;

import com.rhis.api.exception.UserConflictException;
import com.rhis.api.model.Empleado;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {

    private final RealmResource realmResource;

    public KeycloakService(RealmResource realmResource) {
        this.realmResource = realmResource;
    }

    public String createUser(Empleado empleado) throws UserConflictException {
        var userExist = realmResource.users().searchByEmail(empleado.getCorreo(), false)
                .stream().findFirst();

        if (userExist.isPresent()) {
            throw new UserConflictException();
        }

        UserRepresentation userRepresentation = new UserRepresentation();
        CredentialRepresentation credencial = new CredentialRepresentation();

        userRepresentation.setEmail(empleado.getCorreo());
        userRepresentation.setFirstName(empleado.getNombres());
        userRepresentation.setLastName(empleado.getApellidos());
        userRepresentation.setEnabled(true);

        credencial.setType(CredentialRepresentation.PASSWORD);
        credencial.setValue("2580");
        credencial.setTemporary(false);

        userRepresentation.setCredentials(List.of(credencial));

        userRepresentation.setRealmRoles(List.of(empleado.getRole()));

        userRepresentation.setRequiredActions(List.of("UPDATE_PASSWORD"));

        userRepresentation.setUsername(empleado.getUsername());

        var response = realmResource.users().create(userRepresentation);

        realmResource.clearRealmCache();

        String idUserKeycloak = CreatedResponseUtil.getCreatedId(response);

        var roleRepresentation = realmResource.roles()
                .list()
                .stream()
                .filter(roleRepresentation1 -> roleRepresentation1.getName().equalsIgnoreCase(empleado.getRole()))
                .findFirst();

        roleRepresentation.ifPresent(representation -> realmResource
                .users()
                .get(idUserKeycloak)
                .roles()
                .realmLevel()
                .add(Collections.singletonList(representation)));

        return idUserKeycloak;
    }

}
