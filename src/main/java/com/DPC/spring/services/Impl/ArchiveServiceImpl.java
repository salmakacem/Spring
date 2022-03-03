package com.DPC.spring.services.Impl;

import com.DPC.spring.DTO.ArchiveDto;

import com.DPC.spring.Mappers.MappersDto; 
import com.DPC.spring.entities.Archive;
import com.DPC.spring.entities.Evenement;

import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.ArchiveRepository;
import com.DPC.spring.repositories.EvenementRepository;
import com.DPC.spring.services.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.*;

import java.util.List;


import java.util.stream.Collectors;

@Service

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

        List<Archive> listArchive  =this.archiveRepository.findAll();
        return listArchive

                .stream()
                .map(mappersDto::ArchiveToArchiveDto)
                .collect(Collectors.toList());
    }

    @Override

    public ArchiveDto findArchiveByID(long id) {
       Archive archiveData = this.archiveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error: evenement is not found."));
        return mappersDto.ArchiveToArchiveDto(archiveData);
    }

    @Override
    public String affectEventToArchive(long idEvent) {
        Evenement event = this.evenementRepository.findById(idEvent).get();
        event.setStatut(true);
        this.evenementRepository.saveAndFlush(event);
        Archive archive = new Archive();
        archive.setEvenement(event);
        archive.setDate_archivage(new Date());
        this.archiveRepository.save(archive);

        return "archive successfully!";
    }




}

