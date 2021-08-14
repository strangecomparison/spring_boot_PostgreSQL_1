package ru.vasseugs.spring_boot_postgresql_1.domain.guitar;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.CountryEntity;

public interface CountryRepository  extends JpaRepository<CountryEntity, Long> {
}
