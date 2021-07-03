package ru.vasseugs.spring_boot_postgresql_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.spring_boot_postgresql_1.entities.ManufacturerEntity;

public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
}
