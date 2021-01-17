package com.daniel.rooms.service;

import com.daniel.rooms.model.EDaysOfWeek;
import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import com.daniel.rooms.properties.ScheduleProperties;
import com.daniel.rooms.utils.ScheduleCSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final RoomService roomService;
    private final ProfessionalService professionalService;
    private ScheduleProperties scheduleProperties;
    private EmailService emailService;

    @Autowired
    public ScheduleService(RoomService roomService, ProfessionalService professionalService, ScheduleProperties scheduleProperties, EmailService emailService) {
        this.roomService = roomService;
        this.professionalService = professionalService;
        this.scheduleProperties = scheduleProperties;
        this.emailService = emailService;
    }

    public List<Room> generateWeeklySchedule(String day) {

        List<Professional> professionalList = professionalService.findAll();
        List<Room> roomList = roomService.findAll();

        return generateDailySchedule(roomList, professionalList, day);
    }

    private List<Room> generateDailySchedule(List<Room> roomList, List<Professional> professionals, String day) {
        if (roomList.size() <= 0 || professionals.size() <= 0) return roomList;

        professionals.forEach(professional -> professional.setIsBusy(false));

        List<Professional> professionalList = professionals.stream()
                .filter(professional -> professional.getDayofweekList().contains(day)).collect(Collectors.toList());
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

    public void emailSchedule() {

        List<Professional> professionalList = professionalService.findAll();
        List<Room> roomList = roomService.findAll();

        List<List<Room>> lists = new ArrayList<>();

        List<Room> resultMonday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Segunda.getDayOfWeekName());
        lists.add(resultMonday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));
        List<Room> resultTuesday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Terça.getDayOfWeekName());
        lists.add(resultTuesday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));
        List<Room> resultWednesday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Quarta.getDayOfWeekName());
        lists.add(resultWednesday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));
        List<Room> resultThursday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Quinta.getDayOfWeekName());
        lists.add(resultThursday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));
        List<Room> resultFriday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Sexta.getDayOfWeekName());
        lists.add(resultFriday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));
        List<Room> resultSaturday = generateDailySchedule(roomList, professionalList, EDaysOfWeek.Sábado.getDayOfWeekName());
        lists.add(resultSaturday.stream().map(room -> new Room(room.getRoomName(), room.getUnit(), room.getOpenat(), room.getCloseat(), room.isSpecialtyRoom(), room.getProfessional()))
                .collect(Collectors.toList()));


        String fileName = ScheduleCSVHelper.exportSchedule(lists);
        notifyCreation(fileName);
    }

    private void notifyCreation(String fileName) {
        System.out.println("Enviando email de notificacao");
        emailService.sendScheduleByEmail(fileName);
    }
}
