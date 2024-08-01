
package com.beije.account_service_be.system_global.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {

    public Authentication getAuthContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}