package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private RoomService roomService;
    private ProfessionalService professionalService;

    @Autowired
    public ScheduleService(RoomService roomService, ProfessionalService professionalService) {
        this.roomService = roomService;
        this.professionalService = professionalService;
    }

    public List<Room> generateWeeklySchedule() {

        List<Professional> professionalList = professionalService.findAll();
        List<Room> roomList = roomService.findAll();

        return generateDailySchedule(roomList, professionalList);
    }

    private List<Room> generateDailySchedule(List<Room> roomList, List<Professional> professionalList) {
        if (roomList.size() <= 0 || professionalList.size() <= 0) return roomList;

//        int pIndex = 0;

        if (roomList.size() >= professionalList.size()) {
            for (Room room : roomList) {
                if (room.isSpecialtyRoom()) {
                    for (Professional professional : professionalList) {
                        if (professional.isRequiresSpecialtyRoom() && !professional.getIsBusy()) {
                            room.setProfessional(professional);
                            professional.setIsBusy(true);
                            break;
                        }
                    }
                } else {
                    for (Professional professional : professionalList) {
                        if (!professional.getIsBusy()){
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
