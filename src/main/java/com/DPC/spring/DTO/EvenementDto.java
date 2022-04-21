package com.DPC.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class EvenementDto {
    private Long id;

    private String event_name;

    private Boolean statut;

    private Date date;

    private String descriptionEvent;


    private String image;

    private String Adressevent;
}
