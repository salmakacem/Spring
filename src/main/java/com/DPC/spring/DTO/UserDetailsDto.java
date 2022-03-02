package com.DPC.spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    private long id;

    private int CIN;

    private int age;

    private String téléphone;

    private Date date_de_naissance;

    private String nationalité;

    private String sexe;

    private String statut_social;

    private String profession;



}
