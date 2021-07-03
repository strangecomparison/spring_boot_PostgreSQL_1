package ru.vasseugs.spring_boot_postgresql_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "models")
public class GuitarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;

    @OneToMany(
            mappedBy = "guitarModel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<GuitarEntity> guitars;

    public void addGuitar(GuitarEntity guitarEntity) {
        guitars.add(guitarEntity);
        guitarEntity.setGuitarModel(this);
    }

    public void removeGuitar(GuitarEntity guitarEntity) {
        guitars.remove(guitarEntity);
        guitarEntity.setGuitarModel(null);
    }
}
