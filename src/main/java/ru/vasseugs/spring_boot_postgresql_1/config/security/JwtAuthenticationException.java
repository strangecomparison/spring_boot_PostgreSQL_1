package ru.vasseugs.spring_boot_postgresql_1.config.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;

@Getter
public class JwtAuthenticationException extends AuthenticationServiceException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
            super(msg);
            this.httpStatus = httpStatus;
    }
}
