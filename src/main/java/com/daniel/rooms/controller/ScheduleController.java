package com.daniel.rooms.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/schedule")
public class ScheduleController {

    @GetMapping("/")
    public String generateSchedule() {
        System.out.println("generating schedule");
        return "Schedule";
    }

}
