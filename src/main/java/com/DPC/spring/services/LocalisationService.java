package com.DPC.spring.services;

import com.DPC.spring.entities.Localisation;

import java.util.List;

public interface LocalisationService {

    String affectLocalisationToAdres(long idAdress, long idLocal);
    Localisation saveNewLocal(Localisation localisation);

    List<Localisation> getAllLocalisations();
    Localisation findLocalisationsByID(long id);

    String updateLocalisationsByID(long id, Localisation localisation);

    String deleteLocalisationById(long id);
}
