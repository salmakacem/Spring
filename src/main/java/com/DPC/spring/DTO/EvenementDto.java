package com.DPC.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class EvenementDto {
    private Long id;

    private String event_name;

    private String statut;

    private Date date;

    private String description;


    private String image;

    private Double budget;
}
