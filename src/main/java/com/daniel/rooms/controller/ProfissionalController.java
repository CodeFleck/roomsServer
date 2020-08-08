package com.daniel.rooms.controller;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{name}")
    public Professional findByName(@PathVariable String name) {
        return professionalService.findByName(name)
                .orElseThrow(() -> new RuntimeException("Could not find any Professional with the name " + name));
    }

    @PutMapping("/{id}")
    public Professional replaceProfessional(@RequestBody Professional newProfessional, @PathVariable Long id) {

        return professionalService.findById(id)
                .map(professional -> {
                    professional.setName(newProfessional.getName());
                    professional.setBeginat(newProfessional.getBeginat());
                    professional.setEndat(newProfessional.getEndat());
                    professional.setDayofweekList(newProfessional.getDayofweekList());
                    professional.setRequiresSpecialtyRoom(newProfessional.isRequiresSpecialtyRoom());
                    return professionalService.save(professional);
                })
                .orElseGet(() -> {
                    return professionalService.save(newProfessional);
                });
    }

    @DeleteMapping("/{id}")
    void deleteProfessional(@PathVariable Long id) throws Exception {
        Optional<Professional> professional = Optional.of(professionalService.findById(id))
                .orElseThrow(() -> new RuntimeException("Could not find any Professional with id " + id));
        if (professional.isPresent()) {
            professionalService.delete(professional.get());
        }
    }
}
