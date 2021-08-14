package ru.vasseugs.spring_boot_postgresql_1.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vasseugs.spring_boot_postgresql_1.domain.user.User;
import ru.vasseugs.spring_boot_postgresql_1.domain.user.UserRepository;

/**
 * Наша реализация интерфейса UserDetailsService вместо
 * реализации от SpringSecurity, когда мы хранили информацию
 * о пользователях в памяти.
 * По сути, этот сервис призван получать данные о пользователе
 * из БД на основании email.
 */

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // переопределяем этот метод так, что он возвращает нам
    // реализацию интерфейса UserDetails, то есть информацию о пользователе,
    // понятную спрингу.

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
