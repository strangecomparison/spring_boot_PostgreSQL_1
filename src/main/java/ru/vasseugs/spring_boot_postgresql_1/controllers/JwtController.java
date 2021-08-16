package ru.vasseugs.spring_boot_postgresql_1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vasseugs.spring_boot_postgresql_1.config.security.JwtTokenProvider;
import ru.vasseugs.spring_boot_postgresql_1.domain.user.User;
import ru.vasseugs.spring_boot_postgresql_1.domain.user.UserRepository;
import ru.vasseugs.spring_boot_postgresql_1.dto.AuthenticationRequestDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Контроллер, который работает при аутентификации
 * с помощью JWT
 */
@RestController
@RequestMapping("/api/v1/auth")
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtController(AuthenticationManager authenticationManager,
                         UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // аннотация RequestBody означает, что в качестве тела http-запроса
    // будет прикреплет этот объект
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {

        try {
            String email = request.getEmail();
            String password = request.getPassword();

            // аутентифицируем пользователя на основании email и пароля
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            // получаем Entity, чтобы получить его роль - нужна для создания токена
            User user = userRepository.
                    findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));

            // создаем токен
            String token = jwtTokenProvider.createToken(email, user.getRole().name());

            // создаем ответ для данного пользователя
            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid login/password combination",
                    HttpStatus.FORBIDDEN);
        }
    }

    /* Объект SecurityContextLogoutHandler выполняет логаут
    путем внесения изменений в SecurityContextHolder.
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

}
