package ru.vasseugs.spring_boot_postgresql_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.spring_boot_postgresql_1.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // так как главный идентификатор пользователя - это имейл
    Optional<User> findByEmail(String email);
}
