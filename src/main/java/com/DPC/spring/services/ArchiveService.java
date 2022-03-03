package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.ArchiveDto;

import java.util.List;

public interface ArchiveService {
    ArchiveDto saveNewArchiveDto(ArchiveDto archiveDto);
    List<ArchiveDto> getAllArchiveDto();


    ArchiveDto findArchiveByID(long id);

    String affectEventToArchive(long idEvent);


    String UpdateByIdDto(ArchiveDto archiveDto ,long id);
}
