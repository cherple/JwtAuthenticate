package com.example.historical.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * Implementation to retrieve the current logged in user.
 * 
 * @author JX
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // TODO: Use logged in user when Spring Security is enabled.

        return Optional.of("User");
    }

}