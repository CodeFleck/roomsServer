package com.daniel.rooms.repository;

import com.daniel.rooms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	Optional<Room> findByName(String name);
}
