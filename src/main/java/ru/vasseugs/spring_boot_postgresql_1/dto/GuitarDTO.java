package ru.vasseugs.spring_boot_postgresql_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuitarDTO {

    private int guitarId;
    @NotNull
    private int manufacturer;
    @NotNull
    private int model;
    @NotNull
    private int country;
    @NotNull
    @Min(value = 1949, message = "Year of issue should be greater than 1949")
    private int yearOfIssue;
}
