package com.daniel.rooms.controller;

import com.daniel.rooms.model.Room;
import com.daniel.rooms.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        return scheduleService.generateWeeklySchedule(day);
    }

    @PostMapping("/email-schedule")
    public void emailSchedule() {
        scheduleService.emailSchedule();
    }

}
