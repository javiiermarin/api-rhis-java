package com.rhis.api.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@UtilityClass
public class TokenUtils {

    public static boolean isRrhh() {
        return getRolesOfUserLogged().stream()
                .anyMatch(role -> role.equals("rrhh"));
    }

    public static boolean isUser() {
        return getRolesOfUserLogged().stream()
                .anyMatch(role -> role.equals("user"));
    }

    public static String getIdUserLogged() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static List<String> getRolesOfUserLogged() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}
