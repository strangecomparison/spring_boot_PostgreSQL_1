package ru.vasseugs.spring_boot_postgresql_1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuitarModel {
    private int guitarId;
    private int manufacturer;
    private int model;
    private int country;
    private int yearOfIssue;

}
