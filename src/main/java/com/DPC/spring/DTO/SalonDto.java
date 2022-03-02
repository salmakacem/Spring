package com.DPC.spring.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class SalonDto {
    private Long id;
    private Date date;
    private String description;
}
