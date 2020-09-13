package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
        Stack<Room> results = new Stack<>();

        for (Room room : roomList) {
            if(!roomList.isEmpty()) {
                if (!professionalList.isEmpty()){
                    for(int i=0; i < roomList.size(); i++) {
                        roomList.get(i).setProfessional(professionalList.get(0));
                        results.push(roomList.get(i));
                        professionalList.remove(0);
                        roomList.remove(i);
                    }
                }
            }
        }

        System.out.println("Results: ");
        Iterator iterate_value = results.iterator();
        while(iterate_value.hasNext()) {
            Room room = (Room) iterate_value.next();
            System.out.println("Room: " + room.getId() + ":" + room.getRoomName() + " | " + room.getProfessional().getId() + ":" + room.getProfessional().getName() );
        }
        return results;
    }
}
