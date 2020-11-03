package com.daniel.rooms.controller;

import com.daniel.rooms.model.Room;
import com.daniel.rooms.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController (ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{day}")
    public List<Room> generateSchedule(@PathVariable String day) {
        System.out.println(day);
        return scheduleService.generateWeeklySchedule(day);
    }
}
