package com.DPC.spring.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssociationDto {
    private Long id;

    private String name;

    private String description;
}
