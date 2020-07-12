package com.daniel.rooms.controller;

import com.daniel.rooms.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/professionals")
public class ProfissionalController {

    private ProfessionalService professionalService;

    @Autowired
    public void setProductService(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/")
    public ResponseEntity getProfessionals() {
        return ResponseEntity.ok(professionalService.getProfessionals());
    }

}
