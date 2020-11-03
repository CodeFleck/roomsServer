package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final RoomService roomService;
    private final ProfessionalService professionalService;

    @Autowired
    public ScheduleService(RoomService roomService, ProfessionalService professionalService) {
        this.roomService = roomService;
        this.professionalService = professionalService;
    }

    public List<Room> generateWeeklySchedule(String day) {

        List<Professional> professionalList = professionalService.findAll();
        List<Room> roomList = roomService.findAll();

        return generateDailySchedule(roomList, professionalList, day);
    }

    private List<Room> generateDailySchedule(List<Room> roomList, List<Professional> professionals, String day) {
        if (roomList.size() <= 0 || professionals.size() <= 0) return roomList;

        List<Professional> professionalList = professionals.stream().filter(professional -> professional.getDayofweekList().contains(day)).collect(Collectors.toList());
        if (roomList.size() >= professionalList.size()) { //more rooms than professionals
            for (Room room : roomList) {
                if (room.isSpecialtyRoom()) { //match specialty rooms
                    for (Professional professional : professionalList) {
                        if (professional.isRequiresSpecialtyRoom() && !professional.getIsBusy()) {
                            room.setProfessional(professional);
                            professional.setIsBusy(true);
                            break;
                        }
                    }
                } else {
                    for (Professional professional : professionalList) {
                        if (!professional.isRequiresSpecialtyRoom() && !professional.getIsBusy()) { //match not specialty
                            room.setProfessional(professional);
                            professional.setIsBusy(true);
                            break;
                        }
                    }
                }
            }
            for (Room room : roomList) {
                if (room.getProfessional() == null) {
                    for (Professional professional : professionalList) { //match remaining
                        if (!professional.getIsBusy()) {
                            room.setProfessional(professional);
                            professional.setIsBusy(true);
                            break;
                        }
                    }
                }
            }
        }
        return roomList;
    }
}
