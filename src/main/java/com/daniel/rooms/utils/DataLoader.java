package com.daniel.rooms.utils;

import com.daniel.rooms.model.*;
import com.daniel.rooms.repository.ProfessionalRepository;
import com.daniel.rooms.repository.RoleRepository;
import com.daniel.rooms.repository.RoomRepository;
import com.daniel.rooms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private ProfessionalRepository professionalRepository;
    private RoomRepository roomRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, ProfessionalRepository professionalRepository,
                      RoomRepository roomRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.professionalRepository = professionalRepository;
        this.roomRepository = roomRepository;
    }

    public void run(ApplicationArguments args) {
        createUsers();
        createProfessionals();
        createRooms();
    }

    private void createRooms() {
        if (roomRepository.findAll().isEmpty()) {
            Room room = new Room();
            room.setRoomName("sala 1");
            room.setUnit("Viaduto");
            room.setOpenat("07:00");
            room.setCloseat("19:00");
            room.setSpecialtyRoom(false);

            Room room2 = new Room();
            room2.setRoomName("sala 2");
            room2.setUnit("Viaduto");
            room2.setOpenat("07:00");
            room2.setCloseat("19:00");
            room2.setSpecialtyRoom(false);

            Room room3 = new Room();
            room3.setRoomName("sala 1");
            room3.setUnit("Saibreira");
            room3.setOpenat("06:00");
            room3.setCloseat("19:00");
            room3.setSpecialtyRoom(true);

            roomRepository.save(room);
            roomRepository.save(room2);
            roomRepository.save(room3);
        }
    }

    private void createProfessionals() {
        if(professionalRepository.findAll().isEmpty()) {
            Professional professional1 = new Professional();
            professional1.setName("Daniel Fleck");
            professional1.setBeginat("08:00");
            professional1.setEndat("13:00");
            professional1.setIsBusy(false);
            List<String> daysOfWeekList = new ArrayList<>();
            daysOfWeekList.add(EDaysOfWeek.Segunda.toString());
            daysOfWeekList.add(EDaysOfWeek.Quarta.toString());
            daysOfWeekList.add(EDaysOfWeek.Sexta.toString());
            professional1.setDayofweekList(daysOfWeekList);

            Professional professional2 = new Professional();
            professional2.setName("Bruna Fleck");
            professional2.setBeginat("08:00");
            professional2.setEndat("14:00");
            professional2.setIsBusy(false);
            List<String> daysOfWeekList2 = new ArrayList<>();
            daysOfWeekList2.add(EDaysOfWeek.Terça.toString());
            daysOfWeekList2.add(EDaysOfWeek.Quinta.toString());
            professional2.setDayofweekList(daysOfWeekList2);

            Professional professional3 = new Professional();
            professional3.setName("Clarisse Lisboa");
            professional3.setBeginat("08:00");
            professional3.setEndat("17:00");
            professional3.setIsBusy(false);
            List<String> daysOfWeekList3 = new ArrayList<>();
            daysOfWeekList3.add(EDaysOfWeek.Sábado.toString());
            professional3.setDayofweekList(daysOfWeekList3);

            professionalRepository.save(professional1);
            professionalRepository.save(professional2);
            professionalRepository.save(professional3);
        }
    }

    private void createUsers() {
        if (!userRepository.findByUsername("user").isPresent()){
            User user = new User("user", "user@gmail.com", encoder.encode("user"));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(ERole.ROLE_USER));
            user.setRoles(roles);
            userRepository.save(user);
        }
        if (!userRepository.findByUsername("moderator").isPresent()){
            User user = new User("moderator", "moderator@gmail.com", encoder.encode("moderator"));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(ERole.ROLE_MODERATOR));
            roles.add(new Role(ERole.ROLE_USER));
            user.setRoles(roles);
            userRepository.save(user);
        }
        if (!userRepository.findByUsername("admin").isPresent()){
            User user = new User("admin", "admin@gmail.com", encoder.encode("admin"));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(ERole.ROLE_ADMIN));
            roles.add(new Role(ERole.ROLE_MODERATOR));
            roles.add(new Role(ERole.ROLE_USER));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
