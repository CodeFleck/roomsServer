package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.repository.ProfessionalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService {

    @Autowired
    ProfessionalDao professionalDao;

    public List<Professional> getProfessionals() {
        List<Professional> listOfProfessionals = professionalDao.getAllProfessionals();
        return listOfProfessionals;
    }

    public Professional getProfessional(String professionalName) {
        Professional professional = professionalDao.getProfessional(professionalName);
        return professional;
    }
}
