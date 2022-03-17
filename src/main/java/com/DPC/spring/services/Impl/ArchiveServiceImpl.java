package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.ArchiveDto;
import com.DPC.spring.Mappers.MappersDto;
import com.DPC.spring.entities.*;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.ArchiveRepository;
import com.DPC.spring.repositories.EvenementRepository;
import com.DPC.spring.services.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    ArchiveRepository archiveRepository;
    @Autowired
    EvenementRepository evenementRepository;

    final MappersDto mappersDto;

    public ArchiveServiceImpl(MappersDto mappersDto) {
        this.mappersDto = mappersDto;
    }


    @Override
    public ArchiveDto saveNewArchiveDto(ArchiveDto archiveDto) {
        Archive archive = mappersDto.ArchiveDtoToArchive(archiveDto);
        Archive saveArchive = archiveRepository.save(archive);
        ArchiveDto archiveDto1 = mappersDto.ArchiveToArchiveDto(saveArchive);
        return archiveDto1;
    }


    @Override
    public List<ArchiveDto> getAllArchiveDto() {
        List<Archive> listArchive = this.archiveRepository.findAll();
        return listArchive
                .stream()
                .map(mappersDto::ArchiveToArchiveDto)
                .collect(Collectors.toList());
    }

    @Override
    public String UpdateByIdDto(ArchiveDto archiveDto, long id) {
        Optional<Archive> archiveData = this.archiveRepository.findById(id);
        if (archiveData.isPresent()) {
            Archive existingArchive = archiveData.orElseThrow(() -> new ResourceNotFoundException("Adress not found"));
            existingArchive.setDate_archivage(archiveDto.getDate_archivage());
            existingArchive.setDescription(archiveDto.getDescription());

            this.archiveRepository.save(existingArchive);


            return "archive updated successfully!";
        } else {
            throw new ResourceNotFoundException("archive not found");
        }

    }
    public String deleteArchiveById(long id)
    {
        Optional<Archive> userDetailsData = this.archiveRepository.findById(id);
        if (userDetailsData.isPresent()) {
            this.archiveRepository.deleteById(id);
            return "archive deleted successfully!";
        } else {
            throw new ResourceNotFoundException("archive not found");
        }
    }

}
