package ru.vasseugs.spring_boot_postgresql_1.domain.guitar;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.spring_boot_postgresql_1.domain.guitar.GuitarEntity;

// первый тип - тип сущности, для которой создан репозиторий, второй - тип ее первичного ключа
public interface GuitarRepository extends JpaRepository<GuitarEntity, Long> {

}
