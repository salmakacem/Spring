package com.DPC.spring.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class ArchiveDto {

    private Long id;
    private String description;

    private Date date_archivage;
}
