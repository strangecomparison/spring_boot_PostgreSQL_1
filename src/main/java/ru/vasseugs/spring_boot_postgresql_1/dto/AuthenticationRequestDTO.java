package ru.vasseugs.spring_boot_postgresql_1.dto;

import lombok.Data;

/**
 * Это DTO используется для передачи логина и пароля.
 * Его будет принимать контроллер JwtController
 */

@Data
public class AuthenticationRequestDTO {

    private String email;
    private String password;
}
