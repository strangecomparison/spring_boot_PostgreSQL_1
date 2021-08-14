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
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="country_name")
    private String countryName;

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<GuitarEntity> guitars;

    public void addGuitar(GuitarEntity guitarEntity) {
        guitars.add(guitarEntity);
        guitarEntity.setCountry(this);
    }

    public void removeGuitar(GuitarEntity guitarEntity) {
        guitars.remove(guitarEntity);
        guitarEntity.setCountry(null);
    }
}
