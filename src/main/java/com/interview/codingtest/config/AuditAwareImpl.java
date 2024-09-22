package com.interview.codingtest.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Loging user Id can be set here by getting it from spring security context. security isn't implemented
        return Optional.empty();
    }

}
