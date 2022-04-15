package love.korni.shopexample.service.impl;

import love.korni.shopexample.service.SecurityService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * SecurityServiceImpl
 *
 * @author Sergei_Konilov
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void checkIsAdmin() {
        log.trace("Access granted by ROLE_ADMIN authority for user [{}]",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void checkIsUser() {
        log.trace("Access granted by ROLE_USER authority for user [{}]",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
