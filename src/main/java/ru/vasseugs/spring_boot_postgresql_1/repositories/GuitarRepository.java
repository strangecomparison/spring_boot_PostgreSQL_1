package ru.vasseugs.spring_boot_postgresql_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.spring_boot_postgresql_1.entities.GuitarEntity;

import java.util.List;

// первый тип - тип сущности, для которой создан репозиторий, второй - тип ее первичного ключа
public interface GuitarRepository extends JpaRepository<GuitarEntity, Long> {

}
