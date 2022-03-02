package com.DPC.spring.services.Impl;

import com.DPC.spring.entities.Adress;
import com.DPC.spring.entities.Localisation;
import com.DPC.spring.exceptions.ResourceNotFoundException;
import com.DPC.spring.repositories.AdressRepository;
import com.DPC.spring.repositories.LocalisationRepository;
import com.DPC.spring.services.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LocalisationServiceImpl implements LocalisationService {
    @Autowired
    AdressRepository adressRepository;

    @Autowired
    LocalisationRepository localisationRepository;


    public Localisation saveNewLocal(Localisation localisation)
    {

        return this.localisationRepository.save(localisation);
    }

    public List<Localisation> getAllLocalisations()
    {

        return this.localisationRepository.findAll();
    }

    public Localisation findLocalisationsByID(long id)
    {
        Optional<Localisation> localData = this.localisationRepository.findById(id);
        // Return statement if user exist or null
        return localData.orElseThrow(() -> new ResourceNotFoundException(" localisation not found"));

    }

    public String updateLocalisationsByID(long id, Localisation localisation)
    {
        Optional<Localisation> localData = this.localisationRepository.findById(id);
        if (localData.isPresent()) {
            Localisation existingLocalisation = localData.orElse(null);
            existingLocalisation.setStatistique(localisation.getStatistique());

            // save existingUser in the database
            this.localisationRepository.save(existingLocalisation);
            // return statement
            return "localisation updated successfully!";
        } else {
            throw new ResourceNotFoundException("localisation not found");
        }
    }

    public String deleteLocalisationById(long id)
    {
        Optional<Localisation> localisationData = this.localisationRepository.findById(id);
        if (localisationData.isPresent()) {
            this.localisationRepository.deleteById(id);
            return "Localisation deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Localisation not found");
        }
    }



    public String affectLocalisationToAdres(long idAdress, long idLocal) {
        Optional<Localisation> localisationData = this.localisationRepository.findById(idLocal);
        if (localisationData.isPresent()) {
            Localisation existingLocalisation = localisationData.orElseThrow(() -> new ResourceNotFoundException("localisation not found"));
            Optional<Adress> adressData = this.adressRepository.findById(idAdress);
            if (adressData.isPresent()) {
                Adress existingAdress = adressData.orElseThrow(() -> new ResourceNotFoundException("Adress not found"));
                //existingAdress.setLocalisation(existingLocalisation);
                existingLocalisation.setAdress(existingAdress);
                this.localisationRepository.save(existingLocalisation);
                this.adressRepository.save(existingAdress);
            }
        }
        return "localisation affected to details successfully!";


    }
}
