package ru.vasseugs.spring_boot_postgresql_1.domain.user;

import lombok.Data;
import ru.vasseugs.spring_boot_postgresql_1.security.Role;
import ru.vasseugs.spring_boot_postgresql_1.security.Status;

import javax.persistence.*;


/**
 * Таблица для пользователей, хранящихся в БД
 */

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status;
}
