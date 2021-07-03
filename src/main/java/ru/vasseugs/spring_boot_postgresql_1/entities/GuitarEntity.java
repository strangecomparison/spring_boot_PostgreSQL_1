package ru.vasseugs.spring_boot_postgresql_1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guitars")
public class GuitarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    private ManufacturerEntity manufacturer;

    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "id")
    private GuitarModelEntity guitarModel;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    private CountryEntity country;

    private Integer yearOfIssue;


}
