package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import com.daniel.rooms.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

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
        if (roomList.size() <= 0 || professionalList.size() <=0) return roomList;

        //first run to find matches.
        int pIndex = 0;
        System.out.println("Total number rooms: " + roomList.size());
        System.out.println("Total number of professionals: " + professionalList.size());

        List<Professional> professionalsWaitingList = new ArrayList<>();

        //mais salas do que profissionais
        if (roomList.size() >= professionalList.size()) {
            for (Room room : roomList) {
                if (pIndex < professionalList.size()) {
                    if ((!room.isSpecialtyRoom() && !professionalList.get(pIndex).isRequiresSpecialtyRoom()) ||
                            (room.isSpecialtyRoom() && professionalList.get(pIndex).isRequiresSpecialtyRoom())) {
                        room.setProfessional(professionalList.get(pIndex));
                        pIndex++;
                        System.out.println("Found match! " + room.getRoomName() + " " + professionalList.get(pIndex).getName());
                    }
                }
            }

            System.out.println("=================================================");
            //search for remaining professionals

            System.out.println("professionalsWaitingList size = " + professionalsWaitingList.size());
            //search for remaining empty rooms
//            List<Room> vacantRooms = roomList.stream().filter(room -> room.getProfessional() == null).collect(Collectors.toList());
//            System.out.println("vacantRooms size = " + vacantRooms.size());

//            if (vacantRooms.size() > 0 && professionalsWaitingList.size() > 0) {
//                for (Room vacantRoom : vacantRooms) {
//                    if (!vacantRoom.isSpecialtyRoom()) {
//                        vacantRoom.setProfessional(professionalsWaitingList.stream().findFirst().get());
//                    } else {
//                        Optional<Professional> specialtyProfessional = professionalsWaitingList.stream()
//                                .filter(professional -> (professional.isRequiresSpecialtyRoom() && professional.getRoom() == null))
//                                .findFirst();
//                        if (specialtyProfessional.isPresent()) {
//                            vacantRoom.setProfessional(specialtyProfessional.get());
//                            professionalsWaitingList.remove(specialtyProfessional.get());
//                        } else {
//                            Optional<Professional> anyProfessional = professionalsWaitingList.stream()
//                                    .filter(professional -> professional.getRoom() == null)
//                                    .findFirst();
//                            if (anyProfessional.isPresent()) {
//                                vacantRoom.setProfessional(anyProfessional.get());
//                                professionalsWaitingList.remove(anyProfessional.get());
//                            }
//                        }
//                    }
//                }
//            }
        }
        return roomList;
    }
}
