package com.DPC.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvenementDto {
    private Long id;

    private String nom_event;

//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(pattern = "HH:mm")
//    private Date debut;
//
//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(pattern = "HH:mm")
//    private Date fin;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    private String descriptionEvent;

     private String adressevent;
}
