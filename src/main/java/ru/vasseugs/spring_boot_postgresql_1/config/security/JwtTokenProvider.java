package ru.vasseugs.spring_boot_postgresql_1.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // нужен, чтобы получить аутентификацию
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityMilliseconds;

    @Value("${jwt.header}")
    private String authorizationHeader;

    public JwtTokenProvider(@Qualifier("userDetailsServiceImpl")UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // для того, чтобы не хранить пароль в открытом виде
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // claims - это полезная информация в полезной нагрузке JWT, пары ключ-значение
    public String createToken(String username, String role) {

        /* subject - это объект, о котором в полезной нагрузке содержится
        информация. устанавливается в виде отдельной пары ключ-значение
        далее видно, что можно помещать туда как в мапу ключ-значения
         */
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        //указываем дату создания токена
        Date now = new Date();
        // и дату истекания его валидности. к текущей дате прибавляем
        // миллисекунды, умноженные на тысячу (секунды)
        // предпочтительнее хранить в файле properties, а не магическим числом
        Date validity = new Date(now.getTime() + validityMilliseconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                //тут ключ шифрования JWT-токена
                .signWith(SignatureAlgorithm.HS256, "vasseugs")
                .compact();
    }
    // метод для валидации токена
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().
                    setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return !jwsClaims.getBody() // получаем данные токена
                    .getExpiration() // проверяем, что время валидности
                    .before(new Date()); // еще не истекло
        } catch (JwtException | IllegalArgumentException e) {

            // бросаем свое исключение
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    // получаем имплементацию интерфейса Authentication
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.
                loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // метод, получающий username пользователя
    public String getUsername(String token) {
        return Jwts.parser().
                setSigningKey(secretKey).
                parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // метод для получения JWT из запроса
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }
}
