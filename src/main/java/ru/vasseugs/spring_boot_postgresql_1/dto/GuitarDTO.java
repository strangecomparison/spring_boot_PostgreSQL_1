package ru.vasseugs.spring_boot_postgresql_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuitarDTO {

    private int guitarId;
    @NotEmpty(message = "should not be empty")
    private int manufacturer;
    @NotEmpty(message = "should not be empty")
    private int model;
    @NotEmpty(message = "should not be empty")
    private int country;
    @NotEmpty(message = "should not be empty")
    @Min(value = 1949, message = "Year of issue should be greater than 1949")
    private int yearOfIssue;
}
