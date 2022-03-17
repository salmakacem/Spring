package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.entities.Evenement;

import java.util.List;

public interface ArchiveService {
    ArchiveDto saveNewArchiveDto(ArchiveDto archiveDto);
    List<ArchiveDto> getAllArchiveDto();

    //String deleteArchiveById(long id);



   // ArchiveDto findArchiveByID(long id);

  //  String affectEventToArchive(long idEvent);

}
