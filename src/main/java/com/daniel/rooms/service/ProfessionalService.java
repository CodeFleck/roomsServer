package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.repository.ProfessionalRepository;
import com.daniel.rooms.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {

    private ProfessionalRepository professionalRepository;

    @Autowired
    public void setProfessionalRepository(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public List<Professional> findAll() {
        List<Professional> professionals = professionalRepository.findAll();
        return professionals;
    }

    public Optional<Professional> findById(Long professionalId) {
        return professionalRepository.findById(professionalId);
    }

    public Professional save(Professional professional) {
        professional.setBeginat(TimeUtil.getTimeFromDate(professional.getBeginat()));
        professional.setEndat(TimeUtil.getTimeFromDate(professional.getEndat()));
        return professionalRepository.save(professional);
    }

    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }

    public Professional updateProfessional(Professional partialUpdate, Professional professional, String attribute) {
        if(partialUpdate.getName() != null) {
            professional.setName(partialUpdate.getName());
        }
        if(partialUpdate.getBeginat() != null) {
            professional.setBeginat(TimeUtil.getTimeFromDate(partialUpdate.getBeginat()));
        }
        if(partialUpdate.getEndat() != null) {
            professional.setEndat(TimeUtil.getTimeFromDate(partialUpdate.getEndat()));
        }
        if(attribute.equals("dayofweekList")) {
            professional.setDayofweekList(partialUpdate.getDayofweekList());
        }
        if(partialUpdate.isRequiresSpecialtyRoom() != null) {
            professional.setRequiresSpecialtyRoom(partialUpdate.isRequiresSpecialtyRoom());
        }
        return professionalRepository.save(professional);
    }
}
