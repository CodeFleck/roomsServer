package com.daniel.rooms.utils;

import com.daniel.rooms.model.*;
import com.daniel.rooms.repository.ProfessionalRepository;
import com.daniel.rooms.repository.RoleRepository;
import com.daniel.rooms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private ProfessionalRepository professionalRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, ProfessionalRepository professionalRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.professionalRepository = professionalRepository;
    }

    public void run(ApplicationArguments args) {
        createUsers();
        createProfessionals();
    }

    private void createProfessionals() {
        if(professionalRepository.findAll().isEmpty()) {
            Professional professional1 = new Professional();
            professional1.setName("Daniel Fleck");
            professional1.setBeginat(LocalTime.of(8,0));
            professional1.setEndat(LocalTime.of(6,0));
            List<String> daysOfWeekList = new ArrayList<>();
            daysOfWeekList.add(EDaysOfWeek.MONDAY.toString());
            daysOfWeekList.add(EDaysOfWeek.WEDNESDAY.toString());
            daysOfWeekList.add(EDaysOfWeek.FRIDAY.toString());
            professional1.setDayofweekList(daysOfWeekList);

            Professional professional2 = new Professional();
            professional2.setName("Bruna Fleck");
            professional2.setBeginat(LocalTime.of(8,0));
            professional2.setEndat(LocalTime.of(6,0));
            daysOfWeekList.add(EDaysOfWeek.TUESDAY.toString());
            daysOfWeekList.add(EDaysOfWeek.THURSDAY.toString());
            professional2.setDayofweekList(daysOfWeekList);

            Professional professional3 = new Professional();
            professional3.setName("Clarisse Lisboa");
            professional3.setBeginat(LocalTime.of(8,0));
            professional3.setEndat(LocalTime.of(6,0));
            daysOfWeekList.add(EDaysOfWeek.SATURDAY.toString());
            professional3.setDayofweekList(daysOfWeekList);

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
