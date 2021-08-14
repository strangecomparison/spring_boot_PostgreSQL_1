package ru.vasseugs.spring_boot_postgresql_1.domain.guitar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manufacturer")
public class ManufacturerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="manufacturer_name")
    private String manufacturerName;

    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<GuitarEntity> guitars;

    public void addGuitar(GuitarEntity guitarEntity) {
        guitars.add(guitarEntity);
        guitarEntity.setManufacturer(this);
    }

    public void removeGuitar(GuitarEntity guitarEntity) {
        guitars.remove(guitarEntity);
        guitarEntity.setManufacturer(null);
    }

}
