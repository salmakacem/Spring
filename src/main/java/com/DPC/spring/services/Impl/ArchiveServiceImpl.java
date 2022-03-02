package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.repositories.ArchiveRepository;
import com.DPC.spring.services.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    ArchiveRepository archiveRepository;

    final MappersDto mappersDto;

    public ArchiveServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }

    @Override
    public ArchiveDto saveNewAdressDto(ArchiveDto archiveDto) {
        return null;
    }

    @Override
    public List<ArchiveDto> getAllAdressDto() {
        return null;
    }

    @Override
    public ArchiveDto UpdateById(ArchiveDto archiveDto, long id) {
        return null;
    }
}
