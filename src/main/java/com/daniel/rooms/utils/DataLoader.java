package com.daniel.rooms.utils;

import com.daniel.rooms.model.ERole;
import com.daniel.rooms.model.Role;
import com.daniel.rooms.model.User;
import com.daniel.rooms.repository.RoleRepository;
import com.daniel.rooms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        createUsers();
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
