package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.entities.Evenement;

import java.util.List;

public interface ArchiveService {
    ArchiveDto saveNewArchiveDto(ArchiveDto archiveDto);
    List<ArchiveDto> getAllArchiveDto();
    String UpdateByIdDto(ArchiveDto archiveDto ,long id);
    String deleteArchiveById(long id);
}
