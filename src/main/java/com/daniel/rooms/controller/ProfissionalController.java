package com.daniel.rooms.controller;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.service.ProfessionalService;
import com.daniel.rooms.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/professionals")
public class ProfissionalController {

    private ProfessionalService professionalService;

    @Autowired
    public void setProfessionalService(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/")
    public List<Professional> findAll() {
        return professionalService.findAll();
    }

    @PostMapping("/")
    public Professional newProfessional(@Valid @RequestBody Professional professional) {
        return professionalService.save(professional);
    }

    @GetMapping("/{id}")
    public Professional findById(@PathVariable Long id) {
        return professionalService.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find any Professional with id " + id));
    }

    @PutMapping("/{id}")
    public Professional replaceProfessional(@RequestBody Professional newProfessional, @PathVariable Long id) {
        return professionalService.findById(id)
                .map(professional -> {
                    professional.setName(newProfessional.getName());
                    professional.setBeginat(TimeUtil.getTimeFromDate(newProfessional.getBeginat()));
                    professional.setEndat(TimeUtil.getTimeFromDate(newProfessional.getEndat()));
                    professional.setDayofweekList(newProfessional.getDayofweekList());
                    professional.setRequiresSpecialtyRoom(newProfessional.isRequiresSpecialtyRoom());
                    return professionalService.save(professional);
                })
                .orElseGet(() -> {
                    return professionalService.save(newProfessional);
                });
    }

    @PatchMapping("/{id}")
    public Professional updateAttribute(@RequestBody Professional partialUpdate, @PathVariable Long id) {
        Professional professional = professionalService.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find any Professional with id " + id));
        return professionalService.updateProfessional(partialUpdate, professional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteById(id);
    }
}
