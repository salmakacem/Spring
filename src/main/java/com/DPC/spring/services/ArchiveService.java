package com.DPC.spring.services;

import com.DPC.spring.DTO.AdressDto;
import com.DPC.spring.DTO.ArchiveDto;

import java.util.List;

public interface ArchiveService {
    ArchiveDto saveNewAdressDto(ArchiveDto archiveDto);
    List<ArchiveDto> getAllAdressDto();
    ArchiveDto UpdateById(ArchiveDto archiveDto ,long id);
}
